﻿I.regist('skin.Default',function(W,D){
  var CSS = function(){/*
.i-ui-Mask-${skin}{
 z-index:992;
 position:absolute;
 margin:0;
 padding:0;
 font-size:0;
 left:0;
 top:0;
 border:0;
}
.i-ui-Loading-${skin}{
 z-index:992;
 position:absolute;
 left:0;
 top:0;
 width:0;
 height:2px;
 margin:0;
 padding:0;
 overflow:hidden;
 font-size:0;
 border:0;
}
.i-ui-Button-${skin}{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 position:relative;
 display:inline-block;
 vertical-align:middle;
 zoom:1;
 text-decoration:none;
 margin:0;
 padding:0.3em 0.6em;
 line-height:1.5em;
 overflow:hidden;
 font-size:1em;
 cursor:pointer;
 text-align:center;
}
.i-ui-Button-${skin}:hover{text-decoration:none;}
.i-ui-Tip-${skin}{
 z-index:2147483647;
 position:absolute;
 margin:0;
 padding:0.5em 1em;
 font-size:1em;
 left:0;
 top:0;
}
.i-ui-Tip-${skin} span{
 white-space:nowrap;
 text-overflow:ellipsis;
}
.i-z-Win-${skin}{
 z-index:992;
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 position:absolute;
 left:0;
 top:0;
 margin:0;
 padding:0;
 overflow:hidden;
 border:0;
}
.i-z-Win-${skin} i{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 font-weight;normal;
 font-style:normal;
}
.i-z-Win-${skin} .i-shadow{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 position:absolute;
 left:0;
 top:0;
 margin:0;
 padding:0;
 overflow:hidden;
 border:0;
 width:100%;
 height:100%;
}
.i-z-Win-${skin} .i-header{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 -webkit-user-select:none;
 -moz-user-select:none;
 -ms-user-select:none;
 -o-user-select:none;
 user-select:none;
 cursor:move;
 position:absolute;
 left:0;
 top:0;
 margin:0;
 padding:0;
 overflow:hidden;
 font-size:1em;
 text-indent:0.3em;
 white-space:nowrap;
 text-overflow:ellipsis;
}
.i-z-Win-${skin} a.i-max{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 outline:none;
 position:absolute;
 right:0;
 top:0;
 margin:0;
 padding:0;
 text-decoration:none;
 border:0;
 text-align:center;
}
.i-z-Win-${skin} a.i-close{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 outline:none;
 position:absolute;
 right:0;
 top:0;
 margin:0;
 padding:0;
 text-decoration:none;
 border:0;
 text-align:center;
}
.i-z-Win-${skin} .i-body{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 position:absolute;
 left:0;
 top:0;
 margin:0;
 padding:0;
 overflow:auto;
}
.i-z-Win-${skin} .i-footer{
 -webkit-box-sizing:border-box;
 -moz-box-sizing:border-box;
 -ms-box-sizing:border-box;
 -o-box-sizing:border-box;
 box-sizing:border-box;
 position:absolute;
 left:0;
 bottom:0;
 margin:0;
 padding:0;
 overflow:hidden;
}


.i-ui-ToolTip-${skin}{z-index:993;position:absolute;margin:0;padding:0;left:0;top:0;}
.i-ui-ToolTip-${skin} b{font-weight:normal;font-style:normal;display:none;}
.i-ui-ToolTip-${skin} b.c{display:block;margin:0;padding:0;-webkit-border-radius:6px;-moz-border-radius:6px;border-radius:6px;}
.i-ui-ToolTip-${skin} b.content{display:block;margin:10px;padding:0;}
.i-ui-ToolTip-${skin} b.s{display:block;margin:0;padding:0;margin-left:10px;margin-top:-1px;height:10px;overflow:hidden;}
.i-ui-ToolTip-${skin} b.arrow-s0{display:block;margin:0;padding:0;margin-top:-12px;font-family:SimSun;overflow:hidden;font-size:20px;}
.i-ui-ToolTip-${skin} b.arrow-s1{display:block;margin:0;padding:0;margin-top:-24px;font-family:SimSun;overflow:hidden;font-size:20px;}
.i-ui-ToolTip-${skin} b.n{display:block;margin-left:10px;height:10px;}
.i-ui-ToolTip-${skin} b.arrow-n0{display:block;margin:0;padding:0;margin-top:-12px;font-family:SimSun;overflow:hidden;font-size:20px;}
.i-ui-ToolTip-${skin} b.arrow-n1{display:block;margin:0;padding:0;margin-top:-22px;font-family:SimSun;overflow:hidden;font-size:20px;}


.i-ui-Notify-${skin}{z-index:2147483647;position:absolute;margin:0;padding:0.5em 1em;font-size:1em;right:20px;bottom:20px;-webkit-border-radius:6px;-moz-border-radius:6px;-ms-border-radius:6px;-o-border-radius:6px;border-radius:6px;}

ul.i-ui-Tree-${skin}{
  margin:0;
  padding:0;
  display:block;
  -webkit-margin-before:0;
  -webkit-margin-after:0;
  -webkit-margin-start:0;
  -webkit-margin-end:0;
  -webkit-padding-start:0;
}
ul.i-ui-Tree-${skin} i{
  font-weight:normal;
  font-style:normal;
  font-size:1em;
  margin:0;
  padding:0;
  margin-left:3px;
  cursor:default;
}
ul.i-ui-Tree-${skin} b{
  font-weight:normal;
  font-style:normal;
  font-size:1em;
  margin:0;
  padding:0;
  cursor:default;
}
ul.i-ui-Tree-${skin} b.not-visible{
  filter:alpha(opacity=0);
  -moz-opacity:0;
  opacity:0;
}
ul.i-ui-Tree-${skin} a{
  font-weight:normal;
  font-style:normal;
  margin:0;
  padding:0;
  margin-left:3px;
  text-decoration:none;
  cursor:pointer;
}
ul.i-ui-Tree-${skin} li{
  list-style:none;
  display:block;
  margin:0;
  padding:0;
  line-height:1.5em;
}
ul.i-ui-Tree-${skin} ul{
  margin:0;
  padding:0;
  display:block;
  margin-left:1.5em;
  -webkit-margin-before:0;
  -webkit-margin-after:0;
  -webkit-margin-start:1.5em;
  -webkit-margin-end:0;
  -webkit-padding-start:0;
}
.i-ui-Upload-${skin}{
  position:relative;
  display:inline-block;
  vertical-align:middle;
  zoom:1;
  margin:0;
  padding:0;
  overflow:hidden;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.i-ui-Upload-${skin} b{
  position:absolute;
  margin:0;
  padding:0;
  display:block;
  left:0;
  top:0;
  overflow:hidden;
  text-align:center;
  font-weight:normal;
  font-style:normal;
}
.i-ui-Upload-${skin} i{
  position:absolute;
  margin:0;
  padding:0;
  display:block;
  left:0;
  top:0;
  overflow:hidden;
  font-style:normal;
  text-align:center;
}
.i-ui-Upload-${skin} form{
  position:absolute;
  margin:0;
  padding:0;
  display:block;
  right:0;
  top:0;
}
.i-ui-Upload-${skin} input{
  margin:0;
  padding:0;
  font-size:1000px;
}
.i-ui-Form-${skin}{
  display:block;
  margin:0;
  padding:0;
  overflow:hidden;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.i-ui-Form-${skin} ul{
  clear:both;
  display:block;
  margin:1em;
  padding:0;
  -webkit-margin-before:1em;
  -webkit-margin-after:1em;
  -webkit-margin-start:1em;
  -webkit-margin-end:1em;
  -webkit-padding-start:0;
  zoom:1;
  overflow:hidden;
}
.i-ui-Form-${skin} li{
  float:left;
  display:block;
  list-style:none;
  margin:0;
  padding:0;
  line-height:30px;
}
.i-ui-Form-${skin} input{
  outline:none;
  width:100%;
  padding-left:0.5em;
  padding-right:0.5em;
  font-size:1em;
  height:30px;
  line-height:30px;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
  box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
}
.i-ui-Form-${skin} select{
  outline:none;
  width:100%;
  padding-top:5px;
  padding-bottom:5px;
  padding-left:0.5em;
  padding-right:0.5em;
  font-size:1em;
  height:30px;
  line-height:30px;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
  box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
}
.i-ui-Form-${skin} textarea{
  outline:none;
  width:100%;
  padding-left:0.5em;
  padding-right:0.5em;
  font-size:1em;
  line-height:1.2em;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
  box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
}
.i-ui-Form-${skin} input[type="radio"]{
  display:none;
}
.i-ui-Form-${skin} input[type="checkbox"]{
  display:none;
}

.i-ui-Radio-${skin}{position:relative;display:inline-block;vertical-align:middle;zoom:1;text-decoration:none;margin:0;padding:0.3em 0.6em;line-height:1.6em;overflow:hidden;font-size:1em;-webkit-border-radius:6px;-moz-border-radius:6px;-ms-border-radius:6px;-o-border-radius:6px;border-radius:6px;cursor:pointer;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
.i-ui-Radio-${skin}:hover{text-decoration:none;}
.i-ui-Radio-${skin} b{font-weight:normal;}
.i-ui-Radio-${skin} i{font-weight:normal;font-style:normal;}
.i-ui-Checkbox-${skin}{position:relative;display:inline-block;vertical-align:middle;zoom:1;text-decoration:none;margin:0;padding:0.3em 0.6em;line-height:1.6em;overflow:hidden;font-size:1em;-webkit-border-radius:6px;-moz-border-radius:6px;-ms-border-radius:6px;-o-border-radius:6px;border-radius:6px;cursor:pointer;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
.i-ui-Checkbox-${skin}:hover{text-decoration:none;}
.i-ui-Checkbox-${skin} b{font-weight:normal;}
.i-ui-Checkbox-${skin} i{font-weight:normal;font-style:normal;}

.i-ui-Editor-${skin}{
  display:block;
  margin:0;
  padding:0;
  overflow:hidden;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
  width:100%;
  border:1px solid #D4D4D4;
}
.i-ui-Editor-${skin} div.editor-toolbar{
  display:block;
  margin:0;
  padding:0;
  overflow:hidden;
  width:100%;
  background-color:#FAFAFA;
  border:0;
  border-bottom:1px solid #E1E1E1;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.i-ui-Editor-${skin} div.editor-toolbar i{
  display:block;
  float:left;
  margin:2px;
  padding:0;
  overflow:hidden;
  width:1px;
  height:24px;
  font-size:0;
  background-color:#CED1D8;
  border:0;
  border-right:1px solid #FFF;
  cursor:default;
}
.i-ui-Editor-${skin} div.editor-toolbar b{
  margin-left:2px;
  color:#999;
  font-weight:normal;
}
.i-ui-Editor-${skin} div.editor-toolbar a{
  display:block;
  float:left;
  margin:2px;
  padding:0;
  overflow:hidden;
  background-color:#FAFAFA;
  border:1px solid #FAFAFA;
  height:24px;
  color:#444;
  line-height:24px;
  cursor:pointer;
  font-size:14px;
  padding-left:6px;
  padding-right:6px;
  text-decoration:none;
}
.i-ui-Editor-${skin} div.editor-toolbar a:hover{
  background-color:#D5E1F2;
  border:1px solid #A3BDE3;
  text-decoration:none;
}
.i-ui-Editor-${skin} div.editor-toolbar a.active{
  background-color:#D5E1F2;
  border:1px solid #A3BDE3;
  text-decoration:none;
}
.i-ui-Editor-${skin} div.editor-body{
  display:block;
  margin:0;
  padding:0;
  overflow:hidden;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
  width:100%;
  background-color:#FFF;
  border:0;
  outline:none;
  height:120px;
}
.i-ui-Editor-${skin} iframe{
  display:block;
  margin:0;
  padding:0;
  border:0;
  outline:none;
  width:100%;
  height:100%;
}
.i-ui-Editor-${skin} textarea{
  margin:0;
  padding:0;
  border:0;
  outline:none;
  width:100%;
  height:100%;
  resize:none;
}
ol.i-ui-Code-${skin}{
  margin:0;
  padding:0;
  display:block;
  color:#19469D;
  list-style:decimal;
  background-color:#F3F3F3;
  font-size:1em;
  overflow-x:hidden;
  overflow-y:auto;
  font-family:Monaco, "DejaVu Sans Mono", "Bitstream Vera Sans Mono", Consolas, "Courier New", monospace;
  white-space: pre;
}
ol.i-ui-Code-${skin} li{
  display:list-item;
  line-height:1.4em;
  border:0;
  border-left:1px solid #D1D1D1;
  margin:0;
  padding:0;
  margin-left:4em;
  padding-left:1em;
}
ol.i-ui-Code-${skin} span{
  color:#000;
  font-family:Monaco, "DejaVu Sans Mono", "Bitstream Vera Sans Mono", Consolas, "Courier New", monospace;
  white-space: pre;
}
ol.i-ui-Code-${skin} span.keyword{
  color:#954121;
}
ol.i-ui-Code-${skin} span.comment{
  color:#999;
}
.i-ui-Slider-${skin}{
  display:block;
  border-radius:4px;
  -webkit-border-radius:4px;
  -moz-border-radius:4px;
  font-size:0;
  margin:0;
  padding:0;
  position:relative;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.i-ui-Slider-${skin} b{
  display:block;
  border-radius:4px;
  -webkit-border-radius:4px;
  -moz-border-radius:4px;
  font-size:0;
  margin:0;
  padding:0;
  position:absolute;
  left:0;
  top:0;
  width:0,
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
.i-ui-Slider-${skin} i{
  display:block;
  border-radius:4px;
  -webkit-border-radius:4px;
  -moz-border-radius:4px;
  font-size:0;
  margin:0;
  padding:0;
  position:absolute;
  left:0;
  top:0;
  -webkit-box-sizing:border-box;
  -moz-box-sizing:border-box;
  box-sizing:border-box;
}
  */}+'';
  return {
    getCss:function(){return CSS;}
  };
}+'');