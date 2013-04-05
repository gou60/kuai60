(function($){$.setCookie=function(name,value,options){if(typeof name==='undefined'||typeof value==='undefined')
return false;var str=name+'='+encodeURIComponent(value);if(options.domain)str+='; domain='+options.domain;if(options.path)str+='; path='+options.path;if(options.duration){var date=new Date();date.setTime(date.getTime()+options.duration*24*60*60*1000);str+='; expires='+date.toGMTString();}
if(options.secure)str+='; secure';return document.cookie=str;};$.delCookie=function(name){return $.setCookie(name,'',{duration:-1});};$.readCookie=function(name){var value=document.cookie.match('(?:^|;)\\s*'+name.replace(/([-.*+?^${}()|[\]\/\\])/g,'\\$1')+'=([^;]*)');return(value)?decodeURIComponent(value[1]):null;};$.CooQueryVersion='v 2.0';})(jQuery);

new function($) {
  $.fn.setCursorPosition = function(pos) {
    if ($(this).get(0).setSelectionRange) {
      $(this).get(0).setSelectionRange(pos, pos);
    } else if ($(this).get(0).createTextRange) {
      var range = $(this).get(0).createTextRange();
      range.collapse(true);
      range.moveEnd('character', pos);
      range.moveStart('character', pos);
      range.select();
    }
  }
}(jQuery);

var l = window.location.href;
var currentUser;
function showAnimation(containerId, actionValue){
    var obj = $('#'+containerId),
        pos = obj.offset(),
        ani = $('<div id="vote-ani" class="'+(actionValue > 0 ? "pos" : "neg")+'" style="font-size:10px;">'+(actionValue > 0 ? "赞" : "囧")+"</div>");
        ani.appendTo('body');
    pos.top += $(document).scrollTop();
    pos.left += $(document).scrollLeft();
    ani.offset(pos).css('display', 'block').animate({'font-size': '64px', opacity: 0, left: "-=40px"}, 250, 'linear', function(){ani.remove()});
}

function showAnimationForError(containerId, actionValue){
    var obj = $('#'+containerId),
        pos = obj.offset(),
        ani = $('<div id="vote-ani" class="'+(actionValue > 0 ? "pos" : "neg")+'" style="font-size:10px;">'+("投过啦")+"</div>");
        ani.appendTo('body');
    pos.top += $(document).scrollTop();
    pos.left += $(document).scrollLeft();
    ani.offset(pos).css('display', 'block').animate({'font-size': '64px', opacity: 0, left: "-=40px"}, 250, 'linear', function(){ani.remove()});
}

function showFavorite(containerId, actionValue,msg){

    var obj = $('#'+containerId),
        pos = obj.offset(),
        ani = $('<div id="vote-ani" class="'+(actionValue > 0 ? "pos" : "neg")+'" style="font-size:10px;">'+(msg)+"</div>");
        ani.appendTo('body');
    pos.top += $(document).scrollTop();
    pos.left += $(document).scrollLeft();
    ani.offset(pos).css('display', 'block').animate({'font-size': '64px', opacity: 0, left: "-=40px"}, 250, 'linear', function(){ani.remove()});
}

function SelfXY(){
    var yScrolltop;
    var xScrollleft;
    if (self.pageYOffset || self.pageXOffset) {
        yScrolltop = self.pageYOffset;
        xScrollleft = self.pageXOffset;
    } else if (document.documentElement && document.documentElement.scrollTop || document.documentElement.scrollLeft ){     // Explorer 6 Strict
        yScrolltop = document.documentElement.scrollTop;
        xScrollleft = document.documentElement.scrollLeft;
    } else if (document.body) {// all other Explorers
        yScrolltop = document.body.scrollTop;
        xScrollleft = document.body.scrollLeft;
    }
    arrayPageScroll = new Array(xScrollleft + event.clientX ,yScrolltop + event.clientY) 
    return arrayPageScroll;
}

var _Base64 = {

	// private property
_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

	 // private method for UTF-8 encoding
_utf8_encode : function (string) {
		       string = string.replace(/\r\n/g,"\n");
		       var utftext = "";

		       for (var n = 0; n < string.length; n++) {

			       var c = string.charCodeAt(n);

			       if (c < 128) {
				       utftext += String.fromCharCode(c);
			       }
			       else if((c > 127) && (c < 2048)) {
				       utftext += String.fromCharCode((c >> 6) | 192);
				       utftext += String.fromCharCode((c & 63) | 128);
			       }
			       else {
				       utftext += String.fromCharCode((c >> 12) | 224);
				       utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				       utftext += String.fromCharCode((c & 63) | 128);
			       }

		       }

		       return utftext;
	       },
	  // public method for encoding
	  encode : function (input) {
		  var output = "";
		  var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		  var i = 0;

		  input = _Base64._utf8_encode(input);

		  while (i < input.length) {

			  chr1 = input.charCodeAt(i++);
			  chr2 = input.charCodeAt(i++);
			  chr3 = input.charCodeAt(i++);

			  enc1 = chr1 >> 2;
			  enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			  enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			  enc4 = chr3 & 63;

			  if (isNaN(chr2)) {
				  enc3 = enc4 = 64;
			  } else if (isNaN(chr3)) {
				  enc4 = 64;
			  }

			  output = output +
				  this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
				  this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

		  }

		  return output;
	  }
}

/**
 *  速度标记
 *
 */

function SpeedTester(){
    this.displayControlId = "divSpeed";

    this.goodColor = "#33CC33";
    this.averageColor = "#3300FF";
    this.badColor = "#FF0033";

    this.goodMessage = "仅用{0}秒就载入了整个页面，您的网络真棒";
    this.averageMessage = "载入整个页面用了{0}秒，您的网络马马虎虎";
    this.badMessage = "居然用了{0}秒才把整个页面载入进来，实在是抱歉";
    this.goodSpeed = 10000;
    this.averageSpeed = 50000;
    this.badSpeed = 100000;

    this.beginTest = function()    {
        this.startTime = new Date();

        window.onload = function(){
            var displayControl =document.getElementById(SpeedTester.displayControlId);
            if(!displayControl)return;
            SpeedTester.endTime = new Date();

            var spentTime = SpeedTester.endTime - SpeedTester.startTime;

            if (spentTime <= SpeedTester.goodSpeed){
                displayControl.style.backgroundColor = SpeedTester.goodColor;
                displayControl.title = SpeedTester.goodMessage.replace("{0}", spentTime / 1000);
            }else if (spentTime <= SpeedTester.averageSpeed){
                displayControl.style.backgroundColor = SpeedTester.averageColor;
                displayControl.title = SpeedTester.averageMessage.replace ("{0}", spentTime / 1000);
            }else{
                displayControl.style.backgroundColor = SpeedTester.badColor;
                displayControl.title = SpeedTester.badMessage.replace("{0}", spentTime / 1000);
            }
        }
    }
}

SpeedTester = new SpeedTester();
SpeedTester.beginTest();

function addBookmark(title,url) {
    if (window.sidebar) {
        window.sidebar.addPanel(title, url,"");
    } else if( document.all ) {
        window.external.AddFavorite( url, title);
    } else if( window.opera && window.print ) {
        return true;
    }
    return false;
}

function watch(id){
  $('#favorite-'+id).children('a').attr('href', '/articles/remove_favorite/'+id).html('取消围观').removeClass('star').addClass('stared');
}

function unwatch(id){
  $('#favorite-'+id).children('a').attr('href', '/articles/add_favorite/'+id).html('围观').removeClass('stared').addClass('star');
}

function favorite(id){
 		var favoriteCount = parseInt($('#favorite'+id).text());
 		
       $.get('favorite.htm?articleId='+id, null, function(data){
          //$('#favorite_'+id).toggleClass('favorite_afterclick');
          
          	 if(data==""){
               showFavorite('favorite'+id,1,'已收藏!');
               
               $('#favorite'+id).text(favoriteCount + 1)
             }else{
               showFavorite('favorite'+id,1,'收藏过啦!');
               $('#favorite'+id).text(favoriteCount)
             }
          
      });
   
}

function open_form(id){
    $('#quickform-' + id).show();
    $('#reply-'+id).hide();
}

function close_form(id){
    $('#quickform-' + id).hide();
    $('#reply-'+id).show();
}

function reply(id){
    var f = $('#quickform-' + id);
    $.ajax({
        type: "POST",
        url: f.attr('action'),
        data: f.serialize(),
        success: function(){
            $('#comment-'+id)
        }
    })
    close_form(id);
    return false;
}

function voteup(id, link){
    var posscore = parseInt($('#pos-score-'+id).text());
    showAnimation('pos-score-'+id, 1);
    $('#pos-score-'+id).text(posscore + 1);
    $('#score'+id+' .voteicon').hide();
    $.get(link.href);
    $(link).hide();
    try{
      if('jStore' in jQuery){$.jStore.CurrentEngine.set(id, true)}
    }catch(e){}
    
    return false;
}

function votedown(id, link){
    var negscore = parseInt($('#neg-score-'+id).text());
    showAnimation('neg-score-'+id, -1);
    $('#neg-score-'+id).text(negscore - 1)
    $('#score'+id+' .voteicon').hide();
    $.get(link.href);
    $(link).hide();
    try{
      if('jStore' in jQuery){$.jStore.CurrentEngine.set(id, true)}
    }catch(e){}
    return false;
}


function hidevotelink(id, p, n){
    var posscore,negscore;
    if(typeof p === 'undefined'){
        posscore = parseInt($('#pos-score-'+id).text());
    }else{
        posscore = p;
    }
    if(typeof n === 'undefined'){
        negscore = parseInt($('#neg-score-'+id).text());
    }else{
        negscore = n;
    }

//    console.debug(id);
    $('#pos-score-'+id).text(posscore);
    $('#neg-score-'+id).text(negscore);
    
    //$('#score-'+id).html('<strong><span id="pos-score-'+id+'">' + posscore + '</span></strong>赞<span class="space" style="zoom:1">|</span><strong><span id="neg-score-'+id+'">' + negscore + '</span></strong>囧');
}
var voteQueue=[];
var currentUser =true;
function vote2(id, v){
    if(currentUser){
      var posscore = parseInt($('#pos-score-'+id).text()),
          negscore = parseInt($('#neg-score-'+id).text()),
          d = (v>0?'up':'dn');

      var p=(v>0?'agree=1':'disAgree=1');
      
	$.get('articleVote.htm?articleId='+id+'&'+p,function(data){
	    if(data==""){
	      showAnimation(d+'-'+id, v);
	      v>0 ? posscore++ : negscore--;
	    }else{
	      showAnimationForError(d+'-'+id, v);
		}
      	hidevotelink(id, posscore, negscore);
	}) ;

      
    }else{
      showLoginForm();
      $(document).bind('after_logged_in', function(){
        vote2(id, v);
      })
    }    
}


function getCurUsr( data ) 
{
	
  if(data && data.user)
    currentUser=data.user;
  else
	currentUser = null; 
  vote2m( curId, curV );
}
function mkvotestr( aid, uid , v )
{
	return v>0?_Base64.encode(aid+'+'+uid) : _Base64.encode(aid+'-'+uid);
}

var	curId;
var	curV;
function vote2m(id, v){
     	if( !currentUser ) 
    	{
		if( $.readCookie('auth_token') )
		{	
			curId = id;
			curV = v;
			$.getJSON('/session.js?'+(new Date().getTime()), getCurUsr);
		}
		else
			window.location.href = '/new2/login';
	}
      else{
	var posscore = parseInt($('#pos-score-'+id).text()),
       negscore = parseInt($('#neg-score-'+id).text()),
          d = (v>0?'up':'dn');
      showAnimation(d+'-'+id, v);
    //  $.get('/articles/'+id+'/'+d);
      v>0 ? posscore++ : negscore--;
      hidevotelink(id, posscore, negscore);
	$.get( '/inspect/u.php?u='+mkvotestr(id,currentUser.id,v) ) ;
	}

      //voteQueue.push(v>0?id:-id);
}
function vote3(id, v){
  var posscore = parseInt($('#pos-score-'+id).text()),
      negscore = parseInt($('#neg-score-'+id).text()),
      d = (v>0?'up':'dn');
  showAnimation(d+'-'+id, v);
  $.get('/articles/'+id+'/'+d);
  v>0 ? posscore++ : negscore--;
  hidevotelink2(id, posscore, negscore);
}
function hidevotelink2(id, p, n){
    var posscore = p || parseInt($('#pos-score-'+id).text());
    var negscore = n || parseInt($('#neg-score-'+id).text());
    $('#score-'+id).html('<strong>' + posscore + '</strong> :)<span class="space" style="zoom:1">|</span><strong>' + negscore + '</strong> :(');
}

var COMMENT_WARNING=window.location.hostname.indexOf('qiushi')>0 ?
    '请不要发表与本内容无关的评论'
    :'请不要发表与本内容无关的评论';
function clear_warning(e){
    var t = $(this);
    t.focus();
    if($.trim(t.val()) == COMMENT_WARNING){
        t.val('');
    }
    t.blur(function(){
        var t=$(this);
        if($.trim(t.val())==''){
            t.val(COMMENT_WARNING);
        }
    });
}

function postComment(){
    var e =$(this),f = this.form, fe = $(f);
    var v = $.trim(fe.find('.comment_input').val());
    if(v == '' || v == COMMENT_WARNING){
        return false;
    }
    
    var url = f.action+"?articleId="+$('#articleId').val()+"&content="+$('#content').val();
  
   if($('#anony').attr("checked")==true){
    	url = url +"&anony="+$('#anony').val();
    }
    
    
    $.ajax({
  	url: url,

  	success: function( data ) {
    e.val('发表评论').attr('disabled', false);
        fe.find(".comment_input").val('').height('50px');
        var u = $('#qiushi_comments_'+$('#articleId').val()).children('ul');
        if(u.size()>0){u.append(data);}
        else{
          $('<div>'+data+'</div>').insertBefore(fe);
        }
  	 }
	});
    
  /**  
    $.post(url, 
      function(data){
        e.val('发表评论').attr('disabled', false);
        fe.find(".comment_input").val('').height('50px');
        var u = $('#qiushi_comments_'+$('#articleId').val()).children('ul');
        if(u.size()>0){u.append(data);}
        else{
          $('<div>'+data+'</div>').insertBefore(fe);
        }
    });
    
    */
    
    this.value = ('正在发表');
    this.disabled = true;

    return false;
}
function article_comments_path(id){
  return 'comment.htm?articleId='+id;
}
function loadComments(e){
  var l=$(this);
  var id = /\d+/.exec(l.attr('id'));
  
  if(!id) return;
  id=id[0];
  var comments_el = $('#qiushi_comments_'+id);

  if(comments_el.size() == 0){
      var xx = l.html();
      l.text('...');
      $.get(article_comments_path(id), null, function(data){
          $('#qiushi_counts_'+id).after(data).toggleClass('qiushi_counts_afterclick');
          
          comments_el.show();
          l.html(xx).trigger('loaded');
      });
  }else{
      comments_el.toggle();
      $('#qiushi_counts_'+id).toggleClass('qiushi_counts_afterclick');
  }
  window.location.hash = l.attr('id');
  l.blur();
  e.preventDefault();
}
function showall(id){
  $('.hide', '#qiushi_comments_'+id).toggle();
}

$(document).ready(function(){

    $('input.comment_submit').live('click', postComment);
    $('.comment_input').live('click', clear_warning).live('mouseover', clear_warning);
    $('a.comments').click(loadComments);
    var hash=window.location.hash;
    if(hash.indexOf('#c-') === 0){
        $(hash).click();
    }
});

$(document).keypress(function(e){
  if(e.ctrlKey && e.which == 13 || e.which == 10) {
    var o=e.target;
    if(o.form){
        var f=$(o.form).find('input.comment_submit');
        postComment.call(f[0]);
    }
  }
});


function loadScores(){
    var ids=[];
    $('.article').each(function(i,e){
        var id = parseInt($(e).attr('id').replace('article', ''));
        if(!isNaN(id) && id > 0){
            ids.push(id);
        }
    })
    if(ids.length == 0)return;
    $.getJSON('/scores', {ids:ids.join(' ')}, function(data, status){
        $.each(data, function(id, value){
            var s = value.score;
            $('#pos-score-'+id).text(s.pos);
            $('#neg-score-'+id).text(s.neg);
            if(value.rated){
                hidevotelink(id,s.pos,s.neg);
            }
            if(typeof value.watched != 'undefined'){
              if(value.watched){watch(id);}else{unwatch(id);}
            }
            if(s.public_comments_count==0){
                $('#c-'+id).text('暂无评论');
            }else{
                $('#c-'+id).text(s.public_comments_count+'条评论');
            }
        });
    })
}
$(loadScores);

/*
$(function(){
$('.comment').live('mouseenter', function(){
  $('.comment-score a', this).attr('visibility', 'display');
}).live('mouseleave', function(){
  $('.comment-score a', this).attr('visibility', 'hidden');
})
})*/
function comment_vote(id,s){
  var se = $('#comment-'+id+' .score');
  se.text(parseInt(se.text())+s);
  $('#comment-score-'+id+' a').css('visibility', 'hidden');
  if('jStore' in jQuery){
    if(jQuery.jStore.CurrentEngine.get('c'+id)){return}
    jQuery.jStore.CurrentEngine.set('c'+id, true);
  }
  $.post('/comments/'+id+(s > 0 ? '/up' : '/dn'));
  //return false;
}
$(function(){
    $('#user_login').change(function(){
           {$.get("/users/check_login",{user_login:$(this).val()},
           function(text){$('#insertlogin').html("<h2>"+text+"</h2>");});
           }

       })})
 $(function(){
    $('#user_email').change(function(){
    var email=$('#user_email').val();
       if(isEmail(email)){$.get("/users/check_email",{user_email:$(this).val()},
           function(text){
             /*  if (text!="can_use"){ */
               $('#insertemail').html("<h2>"+text+"</h2>");
               
       });
         }
       else
           {$('#insertemail').html("<h2>请输入正确的邮箱地址</h2>");
           }

       })})
    function isEmail(str){
       var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
       return reg.test(str);
}
$(function(){
    $('#invitation_code').change(function(){
    var invitation_code=$('#invitation_code').val();
            $.get("/users/check_invitation_code",{invitation_code:$(this).val()},
           function(text){$('#insertinvitation_code').html("<h2>"+text+"</h2>");});
       })})
$(function(){
  $('.comment').live('mouseenter', function(){
    $(this).addClass('hover');
    if(currentUser){
      $(this).children('.reply').css('visibility','visible');
      $(this).children('.report').css('display','inline');
    }
  }).live('mouseleave', function(){
    $(this).removeClass('hover');
    $(this).children('.reply').css('visibility', 'hidden')
    $(this).children('.report').css('display','none');
  })
})
function replyComment(comment_id, article_id, floor){
  var form = $('form', '#qiushi_comments_'+article_id), c = $('#comment-'+comment_id);
  $('input[name=comment[parent_id]]',form).val(comment_id);
  
  var t = $('textarea', form),o = t.val();
  nv = '回复'+floor+'L:'+ (o == COMMENT_WARNING ? '' : o);
  t.val(nv);
  $.scrollTo(form, 1000);
  t.focus();
  t.setCursorPosition(nv.length);
}


$('.thumb img').click(function(e){
  var o=$(this);
  var url = o.attr('src');
  
  //alert(o.parents('.thumb'));
  o.parents('.thumb').css('max-height','2000px');
  
  if(o.attr('oldsrc')!=null){
    var tourl = o.attr('oldsrc');
    o.attr('src',tourl);
    o.removeAttr('oldsrc');
   return ;
  }
  
  var index= url.indexOf("path=");
   if(index<0)return;;
  
  var tourl = url.substring(index+5);
  o.attr('src',tourl);
  o.attr('oldsrc',url);
 
});


function shareToQQ(id){
 window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+encodeURIComponent('http://kuai60.com/showArticle.htm?articleId='+id));return false;
}

function shareToSina(id){
  var s=screen,d=document,e=encodeURIComponent,a=$('#url_'+id).text(),u='http://www.kuai60.com/'+$('#url_'+id).attr('href');
  var f='http://v.t.sina.com.cn/share/share.php?',p=['url=',e(u),'&title=',e(a),'&appkey=2924220432'].join('');
  var a=function(){
    if(!window.open(f+p,'mb',['toolbar=0,status=0,resizable=1,width=620,height=450,left=',(s.width-620)/2,',top=',(s.height-450)/2].join('')))u=f+p;
  };
  if(/Firefox/.test(navigator.userAgent)){setTimeout(a,0)}else{a()}
}


function postToQQWb(id){
		 var url=$('#url_'+id).attr('href');
		var _t=$('#url_'+id).text();
		var _url = encodeURIComponent(url);

		if(_t.length > 120){
			_t= _t.substr(0,117)+'...';
		}
		_t = encodeURI(_t);

		var _pic='http://www.kuai60.com/images/kuai60.gif';
		
		var _u = 'http://share.v.t.qq.com/index.php?c=share&a=index&title='+_t+'&url='+_url+'&pic='+_pic;;

		window.open( _u,'', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );
	}

