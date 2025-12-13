/**
 * @author dinesh.jakhar
 * 
 */
function ajaxindicatorstart(text)
	{
		if(jQuery('body').find('#resultLoading').attr('id') != 'resultLoading'){
		jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="images/ajax-loader.gif"><div>'+text+'</div></div><div class="bg"></div></div>');
		}
		
		jQuery('#resultLoading').css({
			'width':'100%',
			'height':'100%',
			'position':'fixed',
			'z-index':'10000000',
			'top':'0',
			'left':'0',
			'right':'0',
			'bottom':'0',
			'margin':'auto'
		});	
		
		jQuery('#resultLoading .bg').css({
			'background':'#000000',
			'opacity':'0.7',
			'width':'100%',
			'height':'100%',
			'position':'absolute',
			'top':'0'
		});
		
		jQuery('#resultLoading>div:first').css({
			'width': '250px',
			'height':'75px',
			'text-align': 'center',
			'position': 'fixed',
			'top':'0',
			'left':'0',
			'right':'0',
			'bottom':'0',
			'margin':'auto',
			'font-size':'16px',
			'z-index':'10',
			'color':'#ffffff'
			
		});

	    jQuery('#resultLoading .bg').height('100%');
        jQuery('#resultLoading').fadeIn(300);
	    jQuery('body').css('cursor', 'wait');
	}

	function ajaxindicatorstop() {
	    jQuery('#resultLoading .bg').height('100%');
        jQuery('#resultLoading').fadeOut(300);
	    jQuery('body').css('cursor', 'default');
	}
	$(document).on({
	    ajaxStart: function() {	    	
	  	 ajaxindicatorstart('loading data.. please wait..');
	    },
	    ajaxSuccess: function() {
	    	ajaxindicatorstop();
	    }
	});
	
 /* jQuery(document).ajaxStart(function () {
   		//show ajax indicator
	  var token = $("meta[name='_csrf']").attr("content");
	  var header = $("meta[name='_csrf_header']").attr("content");
	  $(document).ajaxSend(function(e, xhr, options) {
	  	xhr.setRequestHeader(header, token);
	  });
	 
	  ajaxindicatorstart('loading data.. please wait..');
	  
  }).ajaxStop(function () {
		//hide ajax indicator
		ajaxindicatorstop();
  });*/
  
  
  //Disable right mouse click Script

  var message="Function Disabled!";

  ///////////////////////////////////
 /*function clickIE4() {
	  if (event.button==2){
		  alert(message);
		  return false;
	  }
  }

  function clickNS4(e) {
	  if (document.layers||document.getElementById&&!document.all){
		  if (e.which==2||e.which==3){
			  alert(message);
			  return false;
		  }
	  }
  }

  if (document.layers){
	  document.captureEvents(Event.MOUSEDOWN);
	  document.onmousedown=clickNS4;
  	}else if (document.all&&!document.getElementById){
  		document.onmousedown=clickIE4;
  }

  document.oncontextmenu=new Function("return false");
  /*alert(message);*/