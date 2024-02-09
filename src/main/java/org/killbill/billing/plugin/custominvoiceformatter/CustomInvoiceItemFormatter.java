package org.killbill.billing.plugin.custominvoiceformatter;

import org.joda.time.format.DateTimeFormatter;
import org.killbill.billing.invoice.api.InvoiceItem;
import org.killbill.billing.invoice.template.formatters.DefaultInvoiceItemFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class CustomInvoiceItemFormatter extends DefaultInvoiceItemFormatter {

    private static final Logger logger = LoggerFactory.getLogger(CustomInvoiceItemFormatter.class);

    private final InvoiceItem item;

    private final DateTimeFormatter dateFormatter;

    public CustomInvoiceItemFormatter(String defaultLocale, String catalogBundlePath, InvoiceItem item, DateTimeFormatter dateFormatter, Locale locale, ResourceBundle bundle, ResourceBundle defaultBundle) {
        super(defaultLocale, catalogBundlePath, item, dateFormatter, locale, bundle, defaultBundle);
        this.item = item;
        this.dateFormatter = dateFormatter;

    }

    @Override
    public String getFormattedEndDate() {
        return item.getEndDate() == null ? null : item.getEndDate().minusDays(1).toString(dateFormatter);
    }
}
