<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/nlft" prefix="nlft"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<a id="btn-add-cat">添加分类</a>
<div id="list-cat">
  <c:forEach items="${cats}" var="o">
  <div>
    <a name="del-cat" data-id="${o.id}"></a>
    <a name="mod-cat" data-id="${o.id}"></a>
    <a name="check-cat">
      <b></b>
      <i>${o.name}
      <c:choose>
        <c:when test="${0 eq o.type}">(隐藏)</c:when>
        <c:when test="${2 eq o.type}">(tag)</c:when>
      </c:choose>
      </i>
      <input name="cats" type="checkbox" data-id="${o.id}" />
    </a>
  </div>
  </c:forEach>
</div>
<nlft:tpl id="item-cat">
<a></a>
<a></a>
<a name="check-cat">
  <b></b>
  <i>{$=data.name$}
  {$
    switch(data.type+''){
      case '0':
        printf('(隐藏)');
      break;
      case '2':
        printf('(tag)');
      break;
    }
  $}
  </i>
  <input name="cats" type="checkbox" data-id="{$=data.id$}" />
</a>
</nlft:tpl>
<nlft:tpl id="add-cat">
<div id="form-add">
  <ul>
    <li data-width="30">分类名称：</li>
    <li><input id="cat-name" type="text" /></li>
  </ul>
  <ul>
    <li data-width="30">分类类型：</li>
    <li>
      <select id="cat-type">
        <option value="1">显示</option>
        <option value="0">隐藏</option>
        <option value="2">tag</option>
      </select>
    </li>
  </ul>
</div>
</nlft:tpl>
<nlft:tpl id="mod-cat">
<div id="form-mod">
  <ul>
    <li data-width="30">分类名称：</li>
    <li><input id="cat-name" type="text" value="{$=data.name$}" /></li>
  </ul>
  <ul>
    <li data-width="30">分类类型：</li>
    <li>
      <select id="cat-type">
        <option value="1">显示</option>
        <option value="0">隐藏</option>
        <option value="2">tag</option>
      </select>
    </li>
  </ul>
  <ul>
    <li data-width="30">URL：</li>
    <li><div>action-Cat/page?id={$=data.id$}</div></li>
  </ul>
</div>
</nlft:tpl>
<script>
I.want(function(){
  (function(){
    var arts = {};
    <c:forEach items="${artCats}" var="o">
    arts['${o.id}'] = true;
    </c:forEach>
    var l = I.$('name','cats');
    var o;
    for(var i=0,j=l.length;i<j;i++){
      o = l[i];
      if(arts[o.getAttribute('data-id')]){
        o.checked = 'checked';
      }
    }
  })();
  I.ui.Button.render('btn-add-cat',{
    icon:'fa fa-plus',
    background:'#5bc0de',
    border:'1px solid #46b8da',
    color:'#FFF',
    background_hover:'#31b0d5',
    border_hover:'1px solid #269abc',
    color_hover:'#FFF',
    callback:function(){
      addCat();
    }
  });
  I.ui.Checkbox.render(I.$('name','check-cat'));
  I.ui.Button.render(I.$('name','del-cat'),{
    icon:'fa fa-times',
    border:'0',
    border_hover:'0',
    callback:function(){
      deleteCat(this.dom,this.dom.getAttribute('data-id'));
    }
  });
  I.ui.Button.render(I.$('name','mod-cat'),{
    icon:'fa fa-edit',
    border:'0',
    border_hover:'0',
    callback:function(){
      modifyCat(this.dom,this.dom.getAttribute('data-id'));
    }
  });
});
function deleteCat(dom,id){
  I.want(function(){
    I.z.Confirm.create({
      content:'您确定要删除吗？',
      width:300,
      height:120,
      mask_opacity:30,
      mask_color:'#000',
      mask_close:false,
      yes:function(){
        var inst = this;
        I.net.Rmi.set('id',id);
        I.net.Rmi.call('admin-Cat','delete',function(r){
          inst.close();
          var p = dom.parentNode;
          p.parentNode.removeChild(p);
        });
      }
    });
  });
}
function addCat(){
  I.want(function(){
    I.z.Confirm.create({
      title:'添加分类',
      width:400,
      height:140,
      content:I.util.Template.render(null,I.$('TPL_add-cat').value),
      yes:function(){
        var inst = this;
        I.net.Rmi.set('name',I.$('cat-name').value);
        I.net.Rmi.set('type',I.$('cat-type').value);
        I.net.Rmi.call('admin-Cat','add',function(r) {
          inst.close();
          var li = I.insert('div',I.$('list-cat'));
          li.innerHTML = I.util.Template.render(r,I.$('TPL_item-cat').value);
          var as = I.$(li,'*');
          as[0].setAttribute('data-id',r.id);
          as[1].setAttribute('data-id',r.id);
          I.ui.Checkbox.render(as[2]);
          I.ui.Button.render(as[0],{
            border:'0',
            border_hover:'0',
            icon:'fa fa-times',
            callback:function(){
              deleteCat(this.dom,this.dom.getAttribute('data-id'));
            }
          });
          I.ui.Button.render(as[1],{
            border:'0',
            border_hover:'0',
            icon:'fa fa-edit',
            callback:function(){
              modifyCat(this.dom,this.dom.getAttribute('data-id'));
            }
          });
        });
      }
    });
    I.ui.Form.render('form-add');
  });
}
function modifyCat(dom,id){
  I.want(function(){
    I.net.Rmi.set('id',id);
    I.net.Rmi.call('admin-Cat','detail',function(r){
      I.z.Confirm.create({
        title:'修改',
        width:400,
        height:180,
        mask_opacity:30,
        mask_color:'#000',
        mask_close:false,
        yes_button_label:'确定',
        yes_button_background:'#5bc0de',
        yes_button_border:'1px solid #46b8da',
        yes_button_color:'#FFF',
        yes_button_background_hover:'#31b0d5',
        yes_button_border_hover:'1px solid #269abc',
        yes_button_color_hover:'#FFF',
        content:I.util.Template.render(r,I.$('TPL_mod-cat').value),
        yes:function(){
          var inst = this;
          I.net.Rmi.set('id',id);
          I.net.Rmi.set('name',I.$('cat-name').value);
          I.net.Rmi.set('type',I.$('cat-type').value);
          I.net.Rmi.call('admin-Cat','modify',function(r){
            inst.close();
            var p = dom.parentNode;
            var s = r.name;
            switch(r.type+''){
            case '0':
              s += '(隐藏)';
            break;
            case '2':
              s += '(tag)';
            break;
            }
            I.$(I.$(p,'*')[2],'tag','i')[0].innerHTML = s;
          });
        }
      });
      I.ui.Form.render('form-mod');
      I.$('cat-type').value = r.type;
    });
  });
}
</script>