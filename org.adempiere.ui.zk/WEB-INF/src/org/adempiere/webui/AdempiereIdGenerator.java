/******************************************************************************
 * Copyright (C) 2010 Carlos Ruiz                                             *
 * Copyright (C) 2009 Quality Systems & Solutions - globalqss                 *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.sys.IdGenerator;

/**
 * Id generator for selenium ide recording. 
 * You don't need this to run ztl or zk jq selector based test script but if would help to 
 * code or troubleshoot the test script. 
 * 
 * DON'T use this for other purpose, you have been warned :)
 * 
 * @author Carlos Ruiz
 * @author hengsin
 *
 */
public class AdempiereIdGenerator implements IdGenerator {

	public static final String ZK_LOCATOR_ATTRIBUTE = "_zk_locator";
	
	@Override
	public String nextComponentUuid(Desktop desktop, Component comp, ComponentInfo compInfo) {
		buildLocatorAttribute(comp);
		
		return null;
	}

	private static String getWidgetName(String widgetClass) {
		String name = widgetClass.substring(widgetClass.lastIndexOf(".")+1);
		return name.toLowerCase();
	}

	public static String escapeId(String prefix) {
		Pattern pattern = Pattern.compile("[^a-zA-Z_0-9]");
		Matcher matcher = pattern.matcher(prefix);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(sb, "_");
		}
		matcher.appendTail(sb);
		prefix = sb.toString();
		return prefix;
	}

	@Override
	public String nextDesktopId(Desktop desktop) {
		return null;
	}

	@Override
	public String nextPageUuid(Page page) {
		return null;
	}

	public static void updateZkLocatorAttribute(Component comp) {
		buildLocatorAttribute(comp);
		
		List<Component> childs = comp.getChildren();
		if (childs != null && !childs.isEmpty()) {
			for(Component child : childs) {
				updateZkLocatorAttribute(child);
			}
		}
	}

	private static void buildLocatorAttribute(Component comp) {
		String id = comp.getId(); 
		StringBuilder locatorBuilder = new StringBuilder();
					
		if (!Util.isEmpty(id)) {
			if (id.indexOf(" ") > 0) {
				String widgetName = getWidgetName(comp.getWidgetClass());
				locatorBuilder.append("@")
					.append(widgetName).append("[id")
					.append("=\'").append(id).append("']");
			} else {
				locatorBuilder.append("$").append(id);
			}
		}
		
		if (Util.isEmpty(id)) {
			String attribute = comp.getWidgetAttribute(AdempiereWebUI.WIDGET_INSTANCE_NAME);
			if (attribute != null && attribute.length() > 0) {
				String widgetName = getWidgetName(comp.getWidgetClass());
				locatorBuilder.append("@")
					.append(widgetName).append("[")
					.append(AdempiereWebUI.WIDGET_INSTANCE_NAME)
					.append("=\'").append(attribute).append("']");
			} else {
				locatorBuilder.append("@").append(getWidgetName(comp.getWidgetClass()));
			}
		}
		
		Component parent = comp.getParent();
		while(parent != null) {
			if (Util.isEmpty(id) || (parent instanceof IdSpace)) {
				String parentLocator = parent.getWidgetAttribute(ZK_LOCATOR_ATTRIBUTE);
				if (parentLocator != null && parentLocator.trim().length() > 0) {
					locatorBuilder.insert(0, parentLocator+ " ");
					break;
				}
			}
			parent = parent.getParent();
		}
		
		comp.setWidgetAttribute(ZK_LOCATOR_ATTRIBUTE, locatorBuilder.toString());
	}
}