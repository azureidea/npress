function shareTo(app,title,link){
  switch(app){
  case 'weibo':
    window.open('http://service.weibo.com/share/share.php?pic=&title='+encodeURIComponent(title)+'&url='+encodeURIComponent(link));
    break;
  case 'qzone':
    window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?title='+encodeURIComponent(title)+'&url='+encodeURIComponent(link)+'&summary='+encodeURIComponent(title)+'&site='+encodeURIComponent(link));
    break;
  }
  
}