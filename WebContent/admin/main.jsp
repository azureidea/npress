<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlfe" prefix="nlfe"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/admin/include.jsp" />
<title>管理控制台-${WEB_NAME}</title>
<script type="text/javascript">
FIND_CONTAINER = 'main';
var panel_show = false;
function suit(){
  I.want(function(){
    var r = I.region();
    var rl = I.region(I.$('left'));
    var rp = I.region(I.$('panel'));
    I.$('left').style.height = (r.height-40)+'px';
    I.$('sec').style.left = '-1000px';
    
    I.$('main').style.top = '98px';
    I.$('main').style.width = (r.width-rl.width)+'px';
    I.$('main').style.height = (r.height-98)+'px';
    I.$('panel').style.left = (panel_show?r.width-rp.width:r.width+10)+'px';
    I.$('panel').style.height = (r.height-98)+'px';
    I.$('panel').style.top = '98px';
    if(panel_show){
      //I.$('mask').style.width = r.width+'px';
      //I.$('mask').style.height = r.height+'px';
    }else{
      //I.$('mask').style.width = '0px';
      //I.$('mask').style.height = '0px';
      I.$('panel_close').style.right = '-100px';
    }
  });
}

function hidePanel(){
  panel_show = false;
  suit();
}

function showPanel(u){
  I.want(function(){
    panel_show = true;
    I.$('panel_close').style.right = '8px';
    I.net.Page.find(u,null,I.$('panel'));
    suit();
  });
}

function trOnSelected(table,callback){
  I.want(function(){
      var tbody = I.$(table,'tag','tbody')[0];
      var trs = I.$(tbody,'*');
      I.listen(trs,'click',function(m,e){
          var ipt = I.$(m,'tag','input');
          if(ipt&&ipt.length>0){
              I.each(trs,function(n,i){
                  var ipt1 = I.$(n,'tag','input');
                  if(ipt1&&ipt1.length>0){
                      if(n==m){
                          ipt1[0].checked = 'checked';
                          if(callback){
                            callback(ipt[0].value);
                          }
                      }else{
                          ipt1[0].checked = '';
                      }
                  }      
                  if(n==m){
                      I.cls(n,'current');
                  }else{
                      I.cls(n,'');
                  }
              });
          }
      });
  });
  }
</script>
</head>
<body>
  <div id="top">
    <div id="logo">管理控制台</div>
    <a id="exit" class="fa fa-power-off" href="javascript:void(0);" title="退出" onclick="userLogout();"></a>
    <div id="user">${user.name}</div>
  </div>
  <div id="main"></div>
  <div id="sec"></div>
  <div id="left">
    <ul id="menu"></ul>
  </div>
  <div id="thi"></div>
  <div id="mask"></div>
  <div id="panel"></div>
  <div id="panel_close" class="fa fa-times-circle"></div>
  <script type="text/javascript">
  var menus = [];
  function selectFst(m){
    I.$('thi').style.display = 'none';
    suit();
    var code = m.getAttribute('data-n');
    var li = I.$('menu','*');
    for(var i=0;i<li.length;i++){
      if(code==li[i].getAttribute('data-n')){
        I.cls(li[i],'fst_current');
      }else{
        I.cls(li[i],'fst');
      }
    }
    var u =m.getAttribute('data-u');
    if(u){
      I.net.Page.find(u);
    }
    var sub = null;
    for(var i=0;i<menus.length;i++){
      var n = menus[i];
      if(code==n.n){
        if(n.c){
          sub = n.c;
        }
      }
    }
    if(!sub){
      return;
    }
    I.$('sec').style.left = '-130px';
    var r = I.region(I.$('left'));
    I.$('sec').style.height = r.height+'px';
    I.$('sec').innerHTML = '';
    var ul = I.insert('ul',I.$('sec'));
    I.cls(ul,'sec');
    var div = I.insert('div',ul);
    div.innerHTML = m.getAttribute('data-t');
    for(var i=0;i<sub.length;i++){
      var q = sub[i];
      var li = I.insert('li',ul);
      I.cls(li,'sec');
      li.innerHTML = '<a href="javascript:void(0);">'+q.t+'</a>';
      li.setAttribute('data-n',q.n);
      li.setAttribute('data-t',q.t);
      if(q.u){
        li.setAttribute('data-u',q.u);
      }
      I.listen(I.$(li,'tag','a')[0],'click',function(n,e){
        var p = n.parentNode;
        selectSec(m,p);
      });
    }
    I.want(function(){
      var ani = I.util.Animator.create();
      ani.move(
        'linear',
        function(toX,toY){
          I.$('sec').style.left = toX+'px';
        },
        function(){
        },
        10,
        -130,
        0,
        70,
        0
      );
    });
  }
  function selectSec(m,p){
    I.$('thi').style.display = 'none';
    suit();
    I.want(function(){
      var ani = I.util.Animator.create();
      ani.move(
        'linear',
        function(toX,toY){
          I.$('sec').style.left = toX+'px';
        },
        function(){
        },
        10,
        70,
        0,
        -130,
        0
      );
    });
    var u = p.getAttribute('data-u');
    if(u){
      I.net.Page.find(u);
    }
    var code = m.getAttribute('data-n');
    var sup = null;
    for(var i=0;i<menus.length;i++){
      var n = menus[i];
      if(code==n.n){
        if(n.c){
          sup = n.c;
        }
      }
    }
    if(!sup){
      return;
    }
    code = p.getAttribute('data-n');
    var sub = null;
    for(var i=0;i<sup.length;i++){
      var n = sup[i];
      if(code==n.n){
        if(n.c){
          sub = n.c;
        }
      }
    }
    if(!sub){
      sub = [{
        n:code,
        u:u,
        t:p.getAttribute('data-t')
      }];
    }
    I.$('thi').innerHTML = '';
    I.$('thi').style.display = 'block';
    I.insert('div',I.$('thi'));
    var ul = I.insert('ul',I.$('thi'));
    I.cls(ul,'thi');
    var ftsub = null;
    for(var i=0;i<sub.length;i++){
      var q = sub[i];
      var li = I.insert('li',ul);
      I.cls(li,'thi');
      li.innerHTML = '<a href="javascript:void(0);">'+q.t+'</a>';
      li.setAttribute('data-n',q.n);
      if(q.u){
        li.setAttribute('data-u',q.u);
      }
      I.listen(I.$(li,'tag','a')[0],'click',function(n,e){
        var o = n.parentNode;
        selectThi(m,p,o);
      });
      if(i==0){
        ftsub = li;
      }
    }
    if(ftsub){
      selectThi(m,p,ftsub);
    }
    suit();
  }
  function selectThi(m,p,n){
    var code = n.getAttribute('data-n');
    var li = I.$('thi','tag','li');
    for(var i=0;i<li.length;i++){
      if(code==li[i].getAttribute('data-n')){
        I.cls(li[i],'current');
      }else{
        I.cls(li[i],'');
      }
    }
    if(n.getAttribute('data-u')){
      I.net.Page.find(n.getAttribute('data-u'));
    }
  }
  function initMenu(menu){
    menus = menu;
    I.want(function(){
      for(var i=0;i<menu.length;i++){
        var q = menu[i];
        var li = I.insert('li',I.$('menu'));
        I.cls(li,'fst');
        li.innerHTML = '<a href="javascript:void(0);" class="'+q.n+'" title="'+q.t+'"></a>';
        li.setAttribute('data-n',q.n);
        li.setAttribute('data-t',q.t);
        if(q.u){
          li.setAttribute('data-u',q.u);
        }
        I.listen(I.$(li,'tag','a')[0],'click',function(n,e){
          var m = n.parentNode;
          selectFst(m);
        });
      }
    });
  }
  I.want(function(){
    var r = [
      {n:'art',t:'文章管理',c:[
        {n:'art_add',t:'写文章',u:'admin-Article/pageAdd'},
        {n:'art_list',t:'文章列表',u:'admin-Article/pageList'}
      ]},
      {n:'user',t:'用户管理',c:[
        {n:'user_admin',t:'管理员',u:'admin-User/pageAdmin'}
      ]},
      {n:'module',t:'模块管理',c:[
        {n:'module_add',t:'添加模块',u:'admin-Module/pageAdd'},
        {n:'module_list',t:'模块列表',u:'admin-Module/pageList'}
      ]},
      {n:'link',t:'友情链接',c:[
        {n:'link_add',t:'添加网站',u:'admin-Link/pageAdd'},
        {n:'link_list',t:'网站列表',u:'admin-Link/pageList'}
      ]},
      {n:'config',t:'系统设置',c:[
        {n:'config_page',t:'网站配置',u:'admin-Config/page'}
      ]}
    ];
    initMenu(r);
    
    I.listen(I.$('panel_close'),'click',function(){
      panel_show=false;
      suit();
    });
    
    I.listen(self,'resize',function(){
      suit();
    });
    I.listen(self,'scroll',function(){
      suit();
    });
    I.listen(I.$(),'click',function(o,e){
      try{
        o = e.srcElement || e.target;
        if(I.$('sec')==o){
          return;
        }
        var r = I.region(I.$('sec'));
        if(r.x<0){
          return;
        }
        I.want(function(){
          var ani = I.util.Animator.create();
          ani.move(
            'linear',
            function(toX,toY){
              I.$('sec').style.left = toX+'px';
            },
            function(){
            },
            10,
            r.x,
            0,
            -130,
            0
          );
        });
      }catch(ee){}
    });
    suit();
    I.net.Page.find('admin-Main/home');
  });
  </script>
</body>
</html>