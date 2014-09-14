/**
 * I.ui.ToolTip
 * <i>在dom元素上的提示条</i>
 */
I.regist('ui.ToolTip',function(W,D){
  var CFG = {
    skin:'Default',
    border:'1px solid #B1B1B1',
    arrow_color:'#B1B1B1',
    background:'#FFF',
    color:'#333',
    dom:D.body,
    content:'',
    callback:function(){}
  };
  var Q = {};
  var _create = function(obj){
    var id = 0;
    while(Q[id+'']){
      id++;
    }
    obj.id = id;
    var cfg = obj.config;
    var o = I.insert('div');
    I.cls(o,obj.className);
    o.innerHTML = '<b class="n"><b class="arrow-n0">&#9670;</b><b class="arrow-n1">&#9670;</b></b><b class="c"><b class="content"></b></b><b class="s"><b class="arrow-s0">&#9670;</b><b class="arrow-s1">&#9670;</b></b>';
    obj.layer = o;
    I.$(o,'class','content')[0].innerHTML = cfg.content;
    I.$(o,'class','c')[0].style.border = cfg.border;
    I.$(o,'class','c')[0].style.background = cfg.background;
    I.$(o,'class','content')[0].style.color = cfg.color;
    I.$(o,'class','arrow-s0')[0].style.color = cfg.arrow_color;
    I.$(o,'class','arrow-s1')[0].style.color = cfg.background;
    I.$(o,'class','arrow-n0')[0].style.color = cfg.arrow_color;
    I.$(o,'class','arrow-n1')[0].style.color = cfg.background;
    var pr = I.region(cfg.dom);
    var sl = I.region(o);
    var y = pr.y-sl.height+6;
    obj.layer.style.left = (pr.x)+'px';
    obj.layer.style.top = y+'px';
    var r = I.region();
    if(y<r.y){
      I.$(o,'class','s')[0].style.display = 'none';
      y = pr.y+pr.height+6;
      obj.layer.style.top = y+'px';
    }else{
      I.$(o,'class','n')[0].style.display = 'none';
    }
    I.listen(obj.layer,'click',function(m,e){
      obj.close();
    });
    I.listen(cfg.dom,'focus',function(m,e){
      obj.close();
    });
    I.listen(cfg.dom,'click',function(m,e){
      obj.close();
    });
  };

  var _prepare = function(config){
    var obj = {layer:null,className:null,config:null};
    var cfg = I.ui.Component.initConfig(config,CFG);
    obj.config = cfg;
    obj.className = 'i-ui-ToolTip-'+cfg.skin;
    I.util.Skin.init(cfg.skin);
    _create(obj);
    obj.close = function(){
      try{
        this.layer.parentNode.removeChild(this.layer);
      }catch(e){
        return;
      }
      this.config.callback.call(this);
    };
    return obj;
  };
  return {
    create:function(cfg){return _prepare(cfg);}
  };
}+'');