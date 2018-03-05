package com.topstar.volunteer.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.github.pagehelper.PageInfo;

public class PageTag  extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3688396532713754898L;
	
	private PageInfo page;
	
	@Override
	public int doStartTag() throws JspException {
		if(page != null){
			long total = page.getTotal();
			int currentPage = page.getPageNum();
			int pageSize = page.getPageSize();
			int pageCount = page.getPages();
			
			StringBuilder sf = new StringBuilder();
			sf.append("<div class=\"row\">");
			
			sf.append("<div class=\"col-sm-4\"><label>每次加载<select class=\"form-control input-sm\">");
			if(pageSize == 10){
				sf.append("<option value=\"10\" selected>10</option>");
			}else{
				sf.append("<option value=\"10\">10</option>");
			}
			if(pageSize == 20){
				sf.append("<option value=\"20\" selected>20</option>");
			}else{
				sf.append("<option value=\"20\">20</option>");
			}
			if(pageSize == 50){
				sf.append("<option value=\"50\" selected>50</option>");
			}else{
				sf.append("<option value=\"50\">50</option>");
			}
			if(pageSize == 100){
				sf.append("<option value=\"100\" selected>100</option>");
			}else{
				sf.append("<option value=\"100\">100</option>");
			}
            sf.append("</select>项结果，共有"+total+"条数据");                        	
            sf.append("</label>");
            sf.append("</div>");
                                    
            
            sf.append("<div class=\"col-sm-8\">");
            sf.append("<nav aria-label=\"Page navigation\">");
        	sf.append(" <ul class=\"pagination\" style=\"margin-top: 0px;margin-bottom: 0px;\">");
        	//第一页时，禁用第一页翻页按钮
			if(currentPage == 1){
				sf.append("<li class=\"disabled\">"); 
				sf.append("<a href=\"javascript:void(0)\" aria-label=\"Previous\">");    
				sf.append("<span aria-hidden=\"true\">&laquo;</span>");      
				sf.append("</a>");
				sf.append("</li>");
			}else{
				sf.append("<li>"); 
				sf.append("<a href=\"javascript:void(0)\" aria-label=\"Previous\">");    
				sf.append("<span aria-hidden=\"true\">&laquo;</span>");      
				sf.append("</a>");
				sf.append("</li>");
			}
        	
			
			//最多显示6个页码,此参数必须为偶数
			int showPageNum = 6;
			
			int index = 1;
		    int end = pageCount;
		    if(end > showPageNum){
			    if(currentPage < showPageNum){
			        end = showPageNum;
			    }else if(currentPage >=showPageNum/2 && currentPage < end-showPageNum/2){
			    	index = currentPage - (showPageNum/2+1);
			        end = currentPage + showPageNum/2;
			    }else{
			    	index = end- showPageNum+1;
			    }
			}
			
		    for(int i = index; i <=end;i++){
		    	if( i == currentPage){
		    		sf.append("<li class=\"active\"><a  href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">(current)</span></a></li>");
		    	}else{
		    		sf.append("<li><a href=\"javascript:void(0)\">"+i+"</a></li>");
		    	}
		    }
			
		    //最后一页时，禁用最后一页翻页按钮
			if(currentPage == pageCount){
				sf.append("<li class=\"disabled\">");
				sf.append("<a href=\"javascript:void(0)\" aria-label=\"Next\">");
					sf.append("<span aria-hidden=\"true\">&raquo;</span>");
				sf.append("</a>");
				sf.append("</li>");
			}else{
				sf.append("<li>");
				sf.append("<a href=\"javascript:void(0)\" aria-label=\"Next\">");
					sf.append("<span aria-hidden=\"true\">&raquo;</span>");
				sf.append("</a>");
				sf.append("</li>");
			}
			
			sf.append("<li><span style=\"color: #2C2E2F;border-top: 0;border-bottom: 0;border-right: 0;\">"+currentPage+"/"+pageCount+"</span></li>");
			sf.append("<li><input type=\"text\" style=\"width: 34px;height: 34px;margin-right: 10px;padding: 0px;text-align: center;\" class=\"form-control\" /></li>");
			sf.append("<li><span style=\"float: right;\">GO</span></li>");
			sf.append("</ul>");
			sf.append("</nav>");
			sf.append("</div>");
			
			try {
				this.pageContext.getOut().append(sf.toString());
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return super.doStartTag();
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	
}
