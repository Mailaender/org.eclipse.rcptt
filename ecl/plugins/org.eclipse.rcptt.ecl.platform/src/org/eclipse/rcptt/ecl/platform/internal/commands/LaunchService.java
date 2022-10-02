/*******************************************************************************
 * Copyright (c) 2009, 2019 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ecl.platform.internal.commands;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.rcptt.ecl.core.Command;
import org.eclipse.rcptt.ecl.platform.commands.Launch;
import org.eclipse.rcptt.ecl.platform.internal.PlatformPlugin;
import org.eclipse.rcptt.ecl.runtime.ICommandService;
import org.eclipse.rcptt.ecl.runtime.IProcess;

public class LaunchService implements ICommandService {

	public IStatus service(Command command, IProcess context)
			throws InterruptedException, CoreException {
		Launch cmd = (Launch) command;
		
		String mode = cmd.getMode();
		String name = cmd.getName();
		
		ILaunchConfiguration[] confs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
		for (ILaunchConfiguration conf : confs) {
			if (conf.getName().equals(name)) {
				conf.launch(mode, null);
				return Status.OK_STATUS;
			}
		}
		
		return new Status(Status.ERROR, PlatformPlugin.PLUGIN_ID, "Configuration not found.");
	}

}
