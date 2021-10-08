/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */

package foo;

import ij.IJ;
import ij.ImageJ;
import ij.plugin.PlugIn;

import net.imagej.ImageJService;
import net.imagej.tensorflow.TensorFlowService;

import org.scijava.Context;
import org.scijava.service.SciJavaService;

public class TensorFlow_Test implements PlugIn {

	private TensorFlowService tfService;

	@Override
	public void run(String arg) {
		boolean newContext = false;
		Context ctx = (Context) IJ.runPlugIn("org.scijava.Context", "");
		if (ctx == null) {
			ctx = new Context(ImageJService.class, SciJavaService.class);
			newContext = true;
		}
		tfService = ctx.service(TensorFlowService.class);
		IJ.log("TensorFlow loaded? " + tfService.getStatus().isLoaded());
		tfService.initialize();
		tfService.loadLibrary();
		IJ.log("TensorFlow loaded now? " + tfService.getStatus().isLoaded());

		if (newContext) ctx.dispose(); // we made it, we clean it up
	}

	public static void main(String[] args) throws Exception {
		// start ImageJ
		new ImageJ();

		// run the plugin
		IJ.runPlugIn(TensorFlow_Test.class.getName(), "");
	}
}
