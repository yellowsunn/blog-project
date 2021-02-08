(function($){	
	getAds = function(adsJson,adsInfo,printType,adsType){
	  /*
	    printType : 반응형 PC,Mobile 크기일때 출력인지 PC, Mobile 사이즈 모두 출력할지에 대한 CSS 스타일
	    adsType : 애드센스에서만 사용 가능
	              1 - 일치하는 컨텐츠 광고
	              2 - 컨텐츠 내 자동삽입 광고
	              3 - 플러터 푸터 광고
	  */
	  /*
	    adsJson.adsence
	    adsJson.adfit
	    adsJson.dataAdClientId
	  */
    var strScript = "";
    var cssClass = "";
    if(printType == "pc"){
      cssClass = "ads-print-pc ";
    } else if(printType == "mobile"){
      cssClass = "ads-print-mobile ";
    } else {
      cssClass = printType;
    }
    
    adsInfo = parseAdsInfo(adsInfo);
    if(adsInfo == null) return;
    
    var y = (adsInfo.length > 2)?2:adsInfo.length;
    
    for(var i=0;i<y;i++){
      if( ((adsInfo[i][0] == "애드센스" || adsInfo[i][0].toUpperCase() == "ADSENSE") && adsJson.adsence != "1")
        || ((adsInfo[i][0] == "애드핏" || adsInfo[i][0].toUpperCase() == "ADFIT") && adsJson.adfit != "1")
      ) continue;
      
      var iWidth;var iHeight;
      
      if(adsInfo[i][2] != null && adsInfo[i][2] != "" && adsInfo[i][2] != undefined){
        iWidth = adsInfo[i][2];
      }
      if(adsInfo[i][3] != null && adsInfo[i][3] != "" && adsInfo[i][3] != undefined){
        iHeight = adsInfo[i][3];
      }
      
      var styleWidth = (adsInfo.length > 1)?'width:50%;':'width:100%;';
      
      var styleHeight = (iHeight != null)?'height:'+iHeight+'px;':'';
      var styleFloat = (adsInfo.length > 1 && i == 0)?'float:left;text-align:right;':'clear:both;text-align:center;';
      styleFloat = (adsInfo.length > 1 && i == 1)?'float:right;text-align:left;':styleFloat;
      strScript += '<div style="'+styleWidth+styleHeight+styleFloat+'" ' + 'class="'+cssClass+'">';
      
      if((adsInfo[i][0] == "애드센스" || adsInfo[i][0].toUpperCase() == "ADSENSE") && adsJson.adsence == "1" && adsType == "1"){
        strScript += '<ins class="adsbygoogle" style="display:block" data-ad-format="autorelaxed"';
        strScript += ' data-ad-client="'+adsJson.dataAdClientId+'"';
        strScript += ' data-ad-slot="'+adsInfo[i][1]+'"';
        strScript += '></ins>';
        strScript += '<script>(adsbygoogle = window.adsbygoogle || []).push({});<\/script>';
      } else if((adsInfo[i][0] == "애드센스" || adsInfo[i][0].toUpperCase() == "ADSENSE") && adsJson.adsence == "1" && adsType == "2"){
        strScript += '<ins class="adsbygoogle" style="display:block; text-align:center;" data-ad-layout="in-article" data-ad-format="fluid"';
        strScript += ' data-ad-client="'+adsJson.dataAdClientId+'"';
        strScript += ' data-ad-slot="'+adsInfo[i][1]+'"';
        strScript += '></ins>';
        strScript += '<script>(adsbygoogle = window.adsbygoogle || []).push({});<\/script>';
      } else if((adsInfo[i][0] == "애드센스" || adsInfo[i][0].toUpperCase() == "ADSENSE") && adsJson.adsence == "1" && adsType == "3"){
        strScript += '<ins class="adsbygoogle '+cssClass+'" ';
        if(iWidth == null){
          strScript += ' style="display:block"';
        } else {
          strScript += ' style="display:inline-block;width:'+iWidth+'px;height:'+iHeight+'px"';
        }
        
        strScript += ' data-ad-client="'+adsJson.dataAdClientId+'"';
        strScript += ' data-ad-slot="'+adsInfo[i][1]+'"';
        
        if(iWidth == null && iHeight == null){
          strScript += ' data-ad-format="horizontal"';
          strScript += ' data-full-width-responsive="true"'; 
        }
     
        strScript += '></ins>';
        strScript += '<script>(adsbygoogle = window.adsbygoogle || []).push({});<\/script>';
      } else if((adsInfo[i][0] == "애드센스" || adsInfo[i][0].toUpperCase() == "ADSENSE") && adsJson.adsence == "1" ){
        strScript += '<ins class="adsbygoogle '+cssClass+'" ';
        if(iWidth == null){
          strScript += ' style="display:block"';
        } else {
          strScript += ' style="display:inline-block;width:'+iWidth+'px;height:'+iHeight+'px"';
        }
        
        strScript += ' data-ad-client="'+adsJson.dataAdClientId+'"';
        strScript += ' data-ad-slot="'+adsInfo[i][1]+'"';
        
        if(iWidth == null && iHeight == null){
          strScript += ' data-ad-format="auto"';
          var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;

          if(!isMobile){
            strScript += ' data-full-width-responsive="true"';
          } else {
            strScript += ' data-full-width-responsive="false"';
          } 
        }
     
        strScript += '></ins>';
        strScript += '<script>(adsbygoogle = window.adsbygoogle || []).push({});<\/script>';
      } else if((adsInfo[i][0] == "애드핏" || adsInfo[i][0].toUpperCase() == "ADFIT") && adsJson.adfit == "1"){
        strScript += '<ins class="kakao_ad_area '+cssClass+'" style="display:none;" ';
        strScript += ' data-ad-unit    = "'+adsInfo[i][1]+'" ';
        strScript += ' data-ad-width   = "'+iWidth+'" ';
        strScript += ' data-ad-height  = "'+iHeight+'"></ins>';
        strScript += '<script type="text/javascript" src="//t1.daumcdn.net/kas/static/ba.min.js" async><\/script>';
      }
      
      strScript += '</div>';
    }
    
    /*if(console != null) console.log(strScript);*/
    strScript += '<div style="clear:both;height:10px;"></div>';
    return strScript;
	}
	
	parseAdsInfo = function(adsInfo){
		try{
			adsInfo = adsInfo.replace(/ /g,"");
			adsInfo = adsInfo.replace(/\[/g,"['");
			adsInfo = adsInfo.replace(/,/g,"','");
			adsInfo = adsInfo.replace(/\]/g,"']");
			adsInfo = adsInfo.replace(/\]\[/g,"],[");
			return eval("["+adsInfo+"]");
		} catch(e){
			return null;
		}
	}

	addEvent = function(element,eventName,fn){
		if(element.addEventListener)
			element.addEventListener(eventName, fn, false);
		else if (element.attachEvent)
			element.attachEvent('on' + eventName, fn);
	}

	getSubCategoryHtml = function(strHref){
		var $categoryList = $('#menubar-category').find('.link_item');
		var subCategoryHtml = null;
		
		$categoryList.each(function(index, item){
			if($(item).attr('href') == strHref){
				subCategoryHtml = $(item).siblings('.sub_category_list').html();
        return;
			}
		});

    return subCategoryHtml.replace(/	/g, '');
	}
	
})(jQuery);