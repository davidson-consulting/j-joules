/**
 * 
 */
package jJoules.mesureIt;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import static org.junit.platform.commons.support.AnnotationSupport.isAnnotated;

import jJoules.EnergyMesureIt;
import jJoules.energyDisplay.EnergyPrinter;
import jJoules.energyDomain.EnergyDomain;
import jJoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
public class MesureItExtension implements BeforeAllCallback, BeforeTestExecutionCallback, BeforeEachCallback,
		AfterTestExecutionCallback, AfterAllCallback {
	
	private static final Namespace NAMESPACE = Namespace.create("jJoules","mesureIt","MesureItExtension");
	private static EnergyDomain DOMAIN =  new RaplPackageDomain(0);
	private static EnergyMesureIt ENERGY_MESURE_IT = new EnergyMesureIt(DOMAIN);
	
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		

	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		if (!shouldBeMesureIt(context))
			return;
		ENERGY_MESURE_IT.begin();

	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		if (!shouldBeMesureIt(context))
			return;
		ENERGY_MESURE_IT.begin();
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		if (!shouldBeMesureIt(context))
			return;
		double end = ENERGY_MESURE_IT.end();
//		Map<String,Double> res = new HashMap<String,Double>();
//		res.put(DOMAIN.getDomainName(), end);
//		EnergyPrinter.ENERGY_PRINTER.displayIt(res);
		
		report("Test container",context,end);

	}

	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		if (!shouldBeMesureIt(context))
			return;
		double end = ENERGY_MESURE_IT.end();
//		Map<String,Double> res = new HashMap<String,Double>();
//		res.put(DOMAIN.getDomainName(), end);
//		EnergyPrinter.ENERGY_PRINTER.displayIt(res);
		report("Test",context,end);

	}
	
	private static boolean shouldBeMesureIt(ExtensionContext context) {
		return context.getElement()
				.map(el -> isAnnotated(el, MesureIt.class))
				.orElse(false);
	}
	
	private static void report(String unit, ExtensionContext context, double energy) {
		String message = String.format("%s '%s' took %1.2f mj.", unit, context.getDisplayName(), energy);
		context.publishReportEntry("MesureIt", message);
	}
	
	private enum LaunchEnergyKey {
		CLASS, TEST
	}

}
