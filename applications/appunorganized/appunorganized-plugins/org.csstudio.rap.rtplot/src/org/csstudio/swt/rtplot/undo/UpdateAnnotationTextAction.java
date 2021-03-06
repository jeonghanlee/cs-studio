/*******************************************************************************
 * Copyright (c) 2014 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.csstudio.swt.rtplot.undo;

import org.csstudio.swt.rtplot.Annotation;
import org.csstudio.swt.rtplot.Messages;
import org.csstudio.swt.rtplot.RTPlot;

/** Action to update Annotation text
 *  @author Kay Kasemir
 */
public class UpdateAnnotationTextAction<XTYPE extends Comparable<XTYPE>> extends UndoableAction
{
    final private RTPlot<XTYPE> plot;
    final Annotation<XTYPE> annotation;
    final private String start_text, end_text;

    public UpdateAnnotationTextAction(final RTPlot<XTYPE> plot, final Annotation<XTYPE> annotation,
            final String end_text)
    {
        super(Messages.UpdateAnnotation);
        this.plot = plot;
        this.annotation = annotation;
        this.start_text = annotation.getText();
        this.end_text = end_text;
    }

    @Override
    public void run()
    {
        plot.updateAnnotation(annotation, end_text);
    }

    @Override
    public void undo()
    {
        plot.updateAnnotation(annotation, start_text);
    }
}
