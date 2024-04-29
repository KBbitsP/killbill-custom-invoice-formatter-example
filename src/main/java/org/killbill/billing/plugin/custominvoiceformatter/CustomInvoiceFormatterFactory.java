package org.killbill.billing.plugin.custominvoiceformatter;

import org.killbill.billing.currency.api.CurrencyConversionApi;
import org.killbill.billing.invoice.api.Invoice;
import org.killbill.billing.invoice.api.formatters.InvoiceFormatter;
import org.killbill.billing.invoice.plugin.api.InvoiceFormatterFactory;
import org.killbill.billing.util.callcontext.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class CustomInvoiceFormatterFactory implements InvoiceFormatterFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceFormatterFactory.class);

    public CustomInvoiceFormatterFactory() {
        super();
        logger.info("Creating CustomInvoiceFormatterFactory");
    }

    @Override
    public InvoiceFormatter createInvoiceFormatter(final String defaultLocale, final String catalogBundlePath, 
    		final Invoice invoice, final Locale locale, final CurrencyConversionApi currencyConversionApi, ResourceBundle bundle, 
    		ResourceBundle defaultBundle,TenantContext context) {
        return new CustomInvoiceFormatter(defaultLocale, catalogBundlePath, invoice, locale, currencyConversionApi, bundle, 
        		defaultBundle,context);
    }
}
