<#macro new_p page totalpage url='' params='' maxsteps=8 >  
    <#assign ipage=page?number>  
    <#if maxsteps <= 0>  
        <#assign maxsteps=8>  
    </#if>  
    <#assign offset = ((ipage - 1) / maxsteps)?int>  
    <#assign offsetLast = ((totalpage - 1) / maxsteps)?int>  
    <#-- url附加参数的判断 -->  
    <#assign requestParams = "">  
    <#if (params?? && params != '')>  
        <#assign requestParams = '&' + params>  
    </#if>  
    <div class="ui-paging row fn-right" id="pagination">
     <#-- 前一页 -->  
    <#if ipage gt 1>
	    <a href="${url}?page=${ipage - 1}${requestParams}" class="ui-paging-prev">
		    <i class="iconfont" title="左三角形">&#xF039;</i> 上一页
		</a>
    </#if>  
    <#-- 前组 -->  
    <#if offset gt 0>  
        <a href="${url}?page=${offset * maxsteps}${requestParams}">…</a>  
    </#if>  
    <#-- 当前组中的页号-->  
    <#if (offset + 1) * maxsteps < totalpage>  
        <#assign pagelist = (offset + 1) * maxsteps>  
    <#else>  
        <#assign pagelist = totalpage>  
    </#if>  
    <#if ipage gt 0 && ipage lte totalpage>  
        <#list (offset * maxsteps + 1)..pagelist as num>  
            <#if ipage != num>  
                <a href="${url}?page=${num}${requestParams}" class="ui-paging-item">${num}</a>
            <#else>  
                <a href="${url}?page=${num}${requestParams}" class="ui-paging-item ui-paging-current">${num}</a>
            </#if>  
        </#list>  
    </#if>  
    <#-- 下组 -->  
    <#if offset lt offsetLast>  
        <a href="${url}?page=${(offset + 1) * maxsteps + 1}${requestParams}">…</a>  
    </#if>  
   
    <#-- 后一页 -->  
     <#if ipage lt totalpage>  
		<a href="${url}?page=${ipage + 1}${requestParams}" class="ui-paging-next">
		      下一页 <i class="iconfont" title="右三角形"></i>
		 </a>        
    </#if>
 	<span class="ui-paging-info"><span class="ui-paging-bold">${ipage}/${totalpage}</span>页</span>
		    <span class="ui-paging-which"><input type="text" id="setPageIndexText" value="" name="some_name"></span>
		    <a href="${url}?page=XXX${requestParams}" id="setPageIndexHref" class="ui-paging-info ui-paging-goto">跳转</a>
     </div>
</#macro>  

<#macro p page totalpage url='' params='' maxsteps=8>  
    <#assign ipage=page?number>  
    <#if maxsteps <= 0>  
        <#assign maxsteps=8>  
    </#if>  
    <#assign offset = ((ipage - 1) / maxsteps)?int>  
    <#assign offsetLast = ((totalpage - 1) / maxsteps)?int>  
    <#-- url附加参数的判断 -->  
    <#assign requestParams = "">  
    <#if (params?? && params != '')>  
        <#assign requestParams = '&' + params>  
    </#if>  
    <div class="pager">
     <#-- 前一页 -->  
    <#if ipage gt 1>
        <a href="${url}?page=${ipage - 1}${requestParams}">&lt;  上一页</a>  
    </#if>  
    <#-- 前组-->  
    <#if offset gt 0>  
        <a href="${url}?page=${offset * maxsteps}${requestParams}">…</a>  
    </#if>  
    <#-- 当前组中的页号-->  
    <#if (offset + 1) * maxsteps < totalpage>  
        <#assign pagelist = (offset + 1) * maxsteps>  
    <#else>  
        <#assign pagelist = totalpage>  
    </#if>  
    <#if ipage gt 0 && ipage lte totalpage>  
        <#list (offset * maxsteps + 1)..pagelist as num>  
            <#if ipage != num>  
                <a href="${url}?page=${num}${requestParams}">${num}</a>  
            <#else>  
                <strong>${num}</strong>  
            </#if>  
        </#list>  
    </#if>  
    <#-- 下组 -->  
    <#if offset lt offsetLast>  
        <a href="${url}?page=${(offset + 1) * maxsteps + 1}${requestParams}">…</a>  
    </#if>  
   
    <#-- 后一页 -->  
     <#if ipage lt totalpage>  
        <a href="${url}?page=${ipage + 1}${requestParams}">下一页  &gt; </a>  
    </#if>
     </div>
</#macro>  

<#macro f_p page totalpage jsFunc url='' params='' maxsteps=8>  
    <#assign ipage=page?number>  
    <#if maxsteps <= 0>  
        <#assign maxsteps=8>  
    </#if>  
    <#assign offset = ((ipage - 1) / maxsteps)?int>  
    <#assign offsetLast = ((totalpage - 1) / maxsteps)?int>  
    <#-- url附加参数的判断 -->  
    <#assign requestParams = "">  
    <#if (params?? && params != '')>  
        <#assign requestParams = '&' + params>  
    </#if>  
    <div class="ui-paging row fn-right">
     <#-- 前一页 -->  
    <#if ipage gt 1>
        <a href="javascript:${jsFunc}(${ipage - 1});" class="ui-paging-prev">上一页</a> 
    </#if>  
    <#-- 前组-->  
    <#if offset gt 0>  
        <a href="javascript:${jsFunc}(${offset * maxsteps});">…</a>  
    </#if>  
    <#-- 当前组中的页号-->  
    <#if (offset + 1) * maxsteps < totalpage>  
        <#assign pagelist = (offset + 1) * maxsteps>  
    <#else>  
        <#assign pagelist = totalpage>  
    </#if>  
    <#if ipage gt 0 && ipage lte totalpage>  
        <#list (offset * maxsteps + 1)..pagelist as num>  
            <#if ipage != num>  
                <a href="javascript:${jsFunc}(${num});" class="ui-paging-item">${num}</a>  
            <#else>
            <a class="ui-paging-item ui-paging-current">${num}</a>  
                <!--<span class="current">${num}</span>-->
            </#if>  
        </#list>  
    </#if>  
    <#-- 下组 -->  
    <#if offset lt offsetLast>  
        <a href="javascript:${jsFunc}(${(offset + 1) * maxsteps + 1});">…</a>  
    </#if>  
   
    <#-- 后一页 -->  
     <#if ipage lt totalpage>  
        <a href="javascript:${jsFunc}(${ipage + 1});" class="ui-paging-next">
		      下一页 
		</a>
     </#if>
     </div>
</#macro>  