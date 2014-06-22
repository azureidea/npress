/**
 * 类加载器
 */
(function(W,D){
  W.I = {
    version:'2.0.0',
    ROOT:'auto',
    debug:true
  };
  
  var M = 'icore.js';
  var R = '';
  var L = false;
  var SYN = true;
  var Q = {};
  
  var _dir = function(u){
    var r = '';
    if(!L){
      var c = D.getElementsByTagName('script');
      for(var i=0;i<c.length;i++){
        var s = c[i].getAttribute('src');
        if(!s) continue;
        var j = s.indexOf(M);
        if(j>-1){
          r = s.substr(0,j);
          break;
        }
      }
      _autoRoot(r);
      R = r;
      L = true;
    }else{
      r = R;
    }
    if(u){
      r += u;
    }
    return r;
  };
  
  var _autoRoot = function(r){
    if('auto'!=I.ROOT){
      return;
    }
    var n = r.indexOf('://'); 
    if(n>0){
      r = r.substr(n+3);
    }
    n = r.indexOf('/');
    if(n<0){
      I.ROOT = '';
    }else{
      r = r.substr(n);
      n = r.indexOf('/',1);
      if(n<0){
        I.ROOT = '/';
      }else{
        r = r.substr(0,n);
        if(r.indexOf('.')<0){
          I.ROOT = r;
        }else{
          I.ROOT = '/';
        }
      }
    }
  };
  
  var initPackage = function(k){
    var p = I;
    for(var i=0;i<k.length;i++){
      if(!p[k[i]]){
        p[k[i]]={};
      }
      p = p[k[i]];
    }
    return p;
  };
  
  var initClass = function(klass,code){
    var k = klass.split('.');
    var name = k.pop();
    var p = initPackage(k);
    if(p[name]){
      return p[name];
    }else{
      var o = eval('(false||'+code+')')(W,D);
      p[name] = o;
      return o;
    }
  };
  
  var localLoad = function(c,f){
    if(I.debug){
      I.lang.Store.clear();
      throw 'I.js debug mode';
    }
    var v = I.lang.Store.get('i_js_version');

    if(!v||I.version!=v){
      I.lang.Store.clear();
      I.lang.Store.set('i_js_version',I.version);
      throw 'I.js need update';
    }
    
    var k = I.lang.Store.get(c);
    if(!k){
      throw c+' need update';
    }
    var o = D.createElement('script');
    o.text = k;
    D.getElementsByTagName('head')[0].appendChild(o);
    try{
      o.parentNode.removeChild(o);
    }catch(e){}
    f.apply(f);
  };
  
  var checkLoad = function(c,callback){
    var over = true;
    for(var i=0;i<c.length;i++){
      if(!Q[c[i]]){
        over = false;
        break;
      }
    }
    if(over){
      var mc = [];
      for(var i=0;i<c.length;i++){
        var code = Q[c[i]].c;
        var nc = _depend(code);
        for(var j=0;j<nc.length;j++){
          var o = nc[j];
          if(('|'+mc.join('|')+'|').indexOf('|'+o+'|')<0){
            mc.push(o);
          }
        }
      }
      if(mc.length<1){
        var ro = null;
        for(var i=0;i<c.length;i++){
          var s = c[i];
          var cl = initClass(s,Q[s].c);
          Q[s].l = true;
          if(!ro){
            ro = cl;
          }
        }
        SYN = false;
        callback.call(callback,ro);
      }else{
        load(mc,function(){
          var ro = null;
          for(var i=0;i<c.length;i++){
            var s = c[i];
            var cl = initClass(s,Q[s].c);
            Q[s].l = true;
            if(!ro){
              ro = cl;
            }
          }
          callback.call(callback,ro);
        });
      }
    }
  };
  
  var lazyLoad = function(c,f){
    var o = D.createElement('script');
    o.type = 'text/javascript';
    var src = R+c.replace(/\./g,'/')+'.js?t='+new Date().getTime();
    o.src = src;
    o.onload = function(){
      var first = false;
      try{
        this.parentNode.removeChild(this);
        first = true;
      }catch(e){}
      if(first){
        f.apply(f);
      }
    };
    o.onreadystatechange = function(){
      if(/loaded|complete/.test(this.readyState)){
        var first = false;
        try{
          this.parentNode.removeChild(this);
          first = true;
        }catch(e){}
        if(first){
          f.apply(f);
        }
      }
    };
    D.getElementsByTagName('head')[0].appendChild(o);
  };
  
  var load = function(c,f){
    SYN = true;
    var skip = true;
    for(var i=0;i<c.length;i++){
      var s = c[i];
      if(Q[s]){
        continue;
      }
      skip = false;
      try{
        localLoad(s,function(){
          checkLoad(c,f);
        });
      }catch(e){
        lazyLoad(s,function(){
          checkLoad(c,f);
        });
      }
    }
    if(skip){
      checkLoad(c,f);
    }
  };
  
  var preLoad = function(c,f){
    if(SYN){
      W.setTimeout(function(){
        preLoad(c,f);
      },1);
    }else{
      load(c,f);
    }
  };
  
  _dir();
  
  var F = [
    function(f){W.attachEvent('onload',f);},
    function(f){W.addEventListener('DOMContentLoaded',f,false);}
  ];
  for(var i=0;i<F.length;i++){
    try{
      F[i](function(){
        SYN = false;
      });
      break;
    }catch(e){}
  }
    
  var _regist = function(klass,code){
    Q[klass] = {
      l:false,
      c:code
    };
    if(0==klass.indexOf('lang')){
      try{
        initClass(klass,code);
      }catch(e){
        throw e;
      }
    }else{
      try{
        I.lang.Store.set(klass,'I.regist(\''+klass+'\','+code+'+\'\');');
      }catch(e){}
    }
  };
  
  var _depend = function(code){
    var c = [];
    var s = code;
    var idx = s.indexOf('I.');
    while(idx>-1){
      s = s.substr(idx+2);
      var kh = [];
      kh.push(s.indexOf('('));
      kh.push(s.indexOf('['));
      kh.push(s.indexOf(';'));
      var ix = -1;
      for(var i=0;i<kh.length;i++){
        if(kh[i]<0){
          continue;
        }
        if(ix<0){
          ix = kh[i];
        }else{
          if(kh[i]<ix){
            ix = kh[i];
          }
        }
      }
      
      var q = s.substr(0, ix);
      if(q.indexOf('.')>-1){
        var k = q.substr(0, q.lastIndexOf('.'));
        if(k.indexOf('.')>-1){
          if(('|'+c.join('|')+'|').indexOf('|'+k+'|')<0){
            c.push(k);
          }
        }
      }
      idx = s.indexOf('I.');
    }
    return c;
  };
  
  var _run = function(callback){
    var c = _depend(callback+'');
    if(c.length<1){
      callback.apply(callback);
    }else{
      preLoad(c,callback);
    }
  };
  
  var _get = function(klass,callback){
    var c = [];
    c.push(klass);
    preLoad(c,callback);
  };

  I.regist = function(klass,code){_regist(klass,code);return this;};
  I.dir = function(res){return _dir(res);};
  I.run = function(callback){_run(callback);};
  I.get = function(klass,callback){_get(klass,callback);};
  
  I.regist('lang.Store',function(w,d){
    var _host = location.hostname;
    var _LS = [{
      loaded:false,
      instance:null,
      support:function(){
        if(!this.loaded){
          this.instance = w.localStorage;
          this.loaded = true;
        }
        return this.instance?true:false;
      },
      setItem:function(k,v){
        this.instance.setItem(k,v);
      },
      getItem:function(k){
        return this.instance.getItem(k);
      },
      clear:function(){
       this.instance.clear();
      }
    },
    {
      loaded:false,
      instance:null,
      support:function(){
        if(!this.loaded){
          try{
            var o = d.createElement('input');
            o.type = 'hidden';
            o.style.display = 'none';
            o.addBehavior ("#default#userData");
            d.body.appendChild(o);
            var exp = new Date();
            exp.setDate(exp.getDate()+365);
            o.expires = exp.toUTCString();
            this.instance = o;
          }catch(e){}
          this.loaded = true;
        }
        return this.instance?true:false;
      },
      setItem:function(k,v){
        this.instance.load(_host);
        this.instance.setAttribute(k,v);
        this.instance.save(_host);
      },
      getItem:function(k){
        this.instance.load(_host);
        return this.instance.getAttribute(k);
      },
      clear:function(){
        var exp = new Date();
        exp.setDate(exp.getDate()-2);
        this.instance.expires = exp.toUTCString();
        exp = new Date();
        exp.setDate(exp.getDate()+365);
        this.instance.expires = exp.toUTCString();
      }
    }];
    var _set = function(k,v){
      for(var i=0;i<_LS.length;i++){
        if(_LS[i].support()){
          _LS[i].setItem(k,v);
          break;
        }
      }
    };
    var _get = function(k){
      for(var i=0;i<_LS.length;i++){
        if(_LS[i].support()){
          return _LS[i].getItem(k);
        }
      }
      return null;
    };
    var _clear = function(){
      for(var i=0;i<_LS.length;i++){
        if(_LS[i].support()){
          _LS[i].clear();
          break;
        }
      }
    };
    return {
      set:function(k,v){_set(k,v);},
      get:function(k){return _get(k);},
      clear:function(){_clear();}
    };
  }+'');
  
  I.regist('lang.Core',function(W,D){
    var EACH = {'[object Array]':true,'[object NodeList]':true,'[object HTMLCollection]':true,'[object Arguments]':true};
    var C = [
      function(o,s){o.style.cssText = s;},
      function(o,s){o.setAttribute('style',s);}
    ];
    var S = [
      function(o,s){o.className = s;},
      function(o,s){o.setAttribute('class',s);}
    ];
    var P = [
      function(o,n){o.style.filter='alpha(opacity='+n+')';},
      function(o,n){o.style.opacity = n/100;}
    ];
    var E = [
      function(o,s,f){
        o.attachEvent('on'+s,function(e){
          f(o,W.event || e);
        });
      },
      function(o,s,f){
        o.addEventListener(s,function(e){
          f(o,W.event || e);
        },false);
      }
    ];
    var STYLE = [
      function(s){
        var o = D.createStyleSheet();
        o.cssText = s;
      },
      function(s){
        var o = D.createElement("style");
        o.type = "text/css";
        o.innerHTML = s;
        D.getElementsByTagName("head")[0].appendChild(o);
      }
    ];
    var G = {
      ID:function(o,s){return o.getElementById(s);},
      NAME:function(o,s){return o.getElementsByName(s);},
      TAG:function(o,s){return o.getElementsByTagName(s);},
      CHILD:function(o){
        var c = [];
        for(var i=0;i<o.childNodes.length;i++){
          var m = o.childNodes[i];
          if(1 == m.nodeType){
            c.push(m);
          }
        }
        return c;
      },
      CLASS:function(o,s){
        if(o.getElementsByClassName){
          return o.getElementsByClassName(s);
        }
        var c = [];
        var els = this['NAME'](o,'*');
        var i = els.length;
        var ss = s.replace(/\-/g, '\\-');
        var pattern = new RegExp('(^|\\s)'+ss+'(\\s|$)');
        while(--i>=0){
          if(pattern.test(els[i].className)||pattern.test(els[i].getAttribute('class'))){
            c.push(els[i]);
          }
        }
        return c;
      }
    };
    var A = {
      IN:function(a,b){b.appendChild(a);},
      BEFORE:function(a,b){b.parentNode.insertBefore(a,b);},
      AFTER:function(a,b){b.parentNode.lastChild == b?this['IN'](a,b.parentNode):this['BEFORE'](a,b.nextSibling);}
    };
    var $ = function(o){
      switch(o.length){
        case 1: return typeof o[0] == 'string'?('*'==o[0]?G['CHILD'](D.body):G['ID'](D,o[0])):o[0];
        case 2: return '*'==o[1]?G['CHILD']($([o[0]])):G[o[0].toUpperCase()](D,o[1]);
        case 3: return G[o[1].toUpperCase()]($([o[0]]),o[2]);
      }
      return D;
    };
    var _insert = function(o){
      if(o.length<1) return null;
      var m = typeof o[0] == 'string'?D.createElement(o[0]):o[0];
      switch(o.length){
        case 1: A['IN'](m,D.body);break;
        case 2: A['IN'](m,o[1]);break;
        case 3: A[o[1].toUpperCase()](m,o[2]);break;
      }
      return m;
    };
    var _region = function(o){
      var x = 0,y = 0,w = 0,h = 0;
      switch(o.length){
        case 1:
          var r = _region([]);
          var m = o[0];
          w = m.offsetWidth;
          h = m.offsetHeight;
          if(m.getBoundingClientRect){
            x = m.getBoundingClientRect().left + r.x - D.documentElement.clientLeft;
            y = m.getBoundingClientRect().top + r.y - D.documentElement.clientTop;
          }else for(;m;x+=m.offsetLeft,y+=m.offsetTop,m=m.offsetParent);
          break;
        default:
          x = Math.max(D.documentElement.scrollLeft,D.body.scrollLeft);
          y = Math.max(D.documentElement.scrollTop,D.body.scrollTop);
          w = D.documentElement.clientWidth;
          h = D.documentElement.clientHeight;
      }
      return {'x':x,'y':y,'width':w,'height':h};
    };
    var _listen = function(o,s,f){
      _each(o,function(m,j){
        for(var i=0;i<E.length;i++){
          try{
            E[i]($([m]),s,f);
            break;
          }catch(e){}
        }
      });
    };
    var _opacity = function(o,n){
      _each(o,function(m,j){
        for(var i=0;i<P.length;i++){
          try{
            P[i]($([m]),n);
          }catch(e){}
        }
      });
    };
    var _css = function(o,s){
      _each(o,function(m,j){
        for(var i=0;i<C.length;i++){
          try{
            C[i](m,s);
            break;
          }catch(e){}
        }
      });
    };
    var _cls = function(o,s){
      _each(o,function(m,j){
        for(var i=0;i<S.length;i++){
          try{
            S[i](m,s);
            break;
          }catch(e){}
        }
      });
    };
    var _style = function(s){
      for(var i=0;i<STYLE.length;i++){
        try{
          STYLE[i](s);
          break;
        }catch(e){}
      }
    };
    var _each = function(l,f){
      var tp = Object.prototype.toString.apply(l);
      if(EACH[tp]||(l.length&&(!l.alert)&&('[object String]'!=tp))){
        for(var i=0;i<l.length;i++){
          f(l[i],i);
        }
      }else{
        f(l,0);
      }
    };
   
    I['$'] = function(){return $(arguments);};
    I['region'] = function(){return _region(arguments);};
    I['css'] = function(o,s){_css(o,s);return I;};
    I['cls'] = function(o,s){_cls(o,s);return I;};
    I['trim'] = function(s){return s.replace(/(^\s*)|(\s*$)/g,'');};
    I['insert'] = function(){return _insert(arguments);};
    I['listen'] = function(o,s,f){_listen(o,s,f);return f;};
    I['opacity'] = function(o,n){_opacity(o,n);return I;};
    I['style'] = function(s){_style(s);return I;};
    I['each'] = function(l,f){_each(l,f);return I;};
    
    return {};
  }+'');
})(window,document);