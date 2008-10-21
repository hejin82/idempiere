<%--
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * @author Praveen
--%>

<%@ page import="org.posterita.Constants" %>
<%@ page import="org.posterita.user.*" %>
<%@ page import="org.posterita.beans.*" %>
<%@ page import="org.posterita.struts.pos.POSOrderAction" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>	
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>

<%@ include file="/jsp/include/posHeader.jsp" %>	
<%@ include file="/jsp/include/errors.jsp" %>
<div class="spacingSubmenu">

	<html:link action="/POSInfoAction.do?action=getCurrentDayReport" styleClass="submenu"><pos:element columnName="pos.info.today"/></html:link><br>
	<html:link action="/POSInfoAction.do?action=getCurrentMonthReport" styleClass="submenu"><pos:element columnName="pos.info.current.month"/></html:link><br>						
	<html:link action="/InitViewPOSInfo.do" styleClass="submenu"><pos:element columnName="pos.info.custom"/></html:link>						
		    				
</div>					 					 
<%@ include file="/jsp/include/posFooter.jsp" %>