/*******************************************************************************
 * Copyright (c) 2010 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.csstudio.opibuilder.widgets.actions;

import org.csstudio.opibuilder.actions.AbstractWidgetTargetAction;
import org.csstudio.opibuilder.commands.SetWidgetPropertyCommand;
import org.csstudio.opibuilder.widgets.editparts.GroupingContainerEditPart;
import org.csstudio.opibuilder.widgets.model.GroupingContainerModel;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;

/**The action to lock or unlock children in grouping container.
 * @author Xihui Chen
 *
 */
public class LockUnlockChildrenAction extends AbstractWidgetTargetAction{

	public void run(IAction action) {
		
		final GroupingContainerModel containerModel = getSelectedContainer();
		
		Command cmd = new SetWidgetPropertyCommand(containerModel, 
				GroupingContainerModel.PROP_LOCK_CHILDREN, !containerModel.isLocked()){
			@Override
			public void execute() {
				super.execute();
				selecteWidgets();
			}
			
			@Override
			public void undo() {
				super.undo();
				selecteWidgets();
			}
			
			private void selecteWidgets(){
				if(!containerModel.isLocked())
					containerModel.selectWidgets(containerModel.getChildren(), false);
				else
					containerModel.getParent().selectWidget(containerModel, false);
			}
		};	
		cmd.setLabel(containerModel.isLocked()? "Unlock Children" : "Lock Children");
		execute(cmd);		
		
	}
	
	/**
	 * Gets the widget models of all currently selected EditParts.
	 * 
	 * @return a list with all widget models that are currently selected
	 */
	protected final GroupingContainerModel getSelectedContainer() {
		return ((GroupingContainerEditPart)selection.getFirstElement()).getWidgetModel();
	}

}