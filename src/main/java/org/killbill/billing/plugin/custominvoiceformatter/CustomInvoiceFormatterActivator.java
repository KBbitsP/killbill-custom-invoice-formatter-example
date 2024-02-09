package org.killbill.billing.plugin.custominvoiceformatter;

import org.killbill.billing.invoice.plugin.api.InvoiceFormatterFactory;
import org.killbill.billing.osgi.api.OSGIPluginProperties;
import org.killbill.billing.osgi.libs.killbill.KillbillActivatorBase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class CustomInvoiceFormatterActivator extends KillbillActivatorBase {


    // Ideally this string should match the pluginName on the filesystem, but there is no enforcement
    public static final String PLUGIN_NAME = "custom-invoice-formatter-plugin";


    private InvoiceFormatterFactory invoiceFormatterFactory;
    private ServiceRegistration<InvoiceFormatterFactory> registration = null;

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);

        // create CustomInvoiceFormatterFactory
        invoiceFormatterFactory = new CustomInvoiceFormatterFactory();
        Hashtable<String, Object> properties = new Hashtable<>();
        registration = context.registerService(InvoiceFormatterFactory.class, invoiceFormatterFactory,
                properties); // register factory as OSGi service

        registerCustomInvoiceFormatterFactory(context, invoiceFormatterFactory);

    }

    private void registerCustomInvoiceFormatterFactory(final BundleContext context, final InvoiceFormatterFactory factory) {
        final Hashtable<String, String> props = new Hashtable<String, String>();
        props.put(OSGIPluginProperties.PLUGIN_NAME_PROP, PLUGIN_NAME);
        registrar.registerService(context, InvoiceFormatterFactory.class, factory, props);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        super.stop(context);
        if (registration != null) {
            registration.unregister();
            registration = null;
        }
        // Do additional work on shutdown (optional)
    }

}
