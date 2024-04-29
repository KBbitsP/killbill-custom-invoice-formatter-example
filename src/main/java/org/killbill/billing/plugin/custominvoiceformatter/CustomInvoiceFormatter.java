package org.killbill.billing.plugin.custominvoiceformatter;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.killbill.billing.currency.api.CurrencyConversionApi;
import org.killbill.billing.invoice.api.Invoice;
import org.killbill.billing.invoice.api.InvoiceItem;
import org.killbill.billing.invoice.template.formatters.DefaultInvoiceFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomInvoiceFormatter extends DefaultInvoiceFormatter {

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceFormatter.class);
    private final Invoice invoice;
    private final DateTimeFormatter dateFormatter;
    private final String defaultLocale;
    private final String catalogBundlePath;
    private final Locale locale;
    private final ResourceBundle bundle;
    private final ResourceBundle defaultBundle;

    private final String greeting;


    public CustomInvoiceFormatter(final String defaultLocale,
                                  final String catalogBundlePath, final Invoice invoice, final Locale locale,
                                  final CurrencyConversionApi currencyConversionApi, final ResourceBundle bundle,
                                  final ResourceBundle defaultBundle, final TenantContext context) {
        super(defaultLocale, catalogBundlePath, invoice, locale, currencyConversionApi, bundle, defaultBundle);
        this.defaultLocale = defaultLocale;
        this.catalogBundlePath = catalogBundlePath;
        this.invoice = invoice;
        this.locale = locale;
        this.bundle = bundle;
        this.defaultBundle = defaultBundle;
        this.dateFormatter = DateTimeFormat.mediumDate().withLocale(locale);
        this.greeting = "Here is your new invoice!";
    }


    public String getGreeting() {
        return this.greeting;
    }

    @Override
    public List<InvoiceItem> getInvoiceItems() {
        final List<InvoiceItem> formatters = new ArrayList<InvoiceItem>();
        for (final InvoiceItem item : super.mergeCBAAndCreditAdjustmentItems()) {
            formatters.add(new CustomInvoiceItemFormatter(defaultLocale, catalogBundlePath, item, dateFormatter, locale, bundle, defaultBundle));
        }
        return formatters;
    }

}
