# killbill-custom-invoice-formatter

The custom invoice formatter plugin can be used to customize data in HTML invoices. This is a sample plugin that can be modified as per your custom requirements.

## Overview

Kill Bill invoices can be rendered as HTML using the [Render an invoice as HTML](https://killbill.github.io/slate/invoice.html#render-an-invoice-as-html) API endpoint. Kill Bill provides a [default invoice template](https://github.com/killbill/killbill/blob/99f7102c83cefe892027f4ac0d1ab4da37dd517b/util/src/main/resources/org/killbill/billing/util/email/templates/HtmlInvoiceTemplate.mustache) for generating HTML invoices. Users can also customize invoice data presentation by uploading custom invoice templates as explained in the [invoice templates](https://docs.killbill.io/latest/invoice_templates) document. 

Sometimes, you may need to customize the data in the HTML invoice (add additional fields to the invoice, use custom logic for existing fields in the invoice, etc.). A custom invoice formatter plugin can be used in such cases.

## Build

```
mvn clean install -DskipTests=true 
```

## Installation

Run the following [kpm](https://github.com/killbill/killbill-cloud/blob/master/kpm) command:

```
kpm install_java_plugin 'custom-invoice-formatter' --from-source-file=<jar_file_path> --destination=<path_to_install_plugin>
```
## Configuration

In order to use this plugin, you will need to specify the following config properties:

````
org.killbill.template.invoiceFormatterFactoryPluginName=custom-invoice-formatter-plugin
org.killbill.osgi.system.bundle.export.packages.extra=org.killbill.billing.invoice.template.formatters
````


## Testing the plugin

1. Build and install the plugin
2. Add the configuration properties mentioned above
3. Start Kill Bill
4. [Upload](https://killbill.github.io/slate/invoice.html#upload-the-invoice-template-for-the-tenant) the [Sample HTMLTemplate](https://github.com/killbill/killbill-custom-invoice-formatter-example/blob/main/SampleHTMLTemplate.mustache)
5. Invoke the [Render an invoice as HTML](https://killbill.github.io/slate/invoice.html#render-an-invoice-as-html) API endpoint for an existing invoice
6. Verify that the text "Here is your new invoice!" is displayed at the top and that the invoice item end date is one less that the actual date (This is explained below)

## Customizing this plugin as per your requirements

As mentioned earlier, this is just a demo plugin. You can customize this plugin as per your requirement. 

Some pointers are as below.

To add a new field:

1. Define the field as well as a getter method corresponding to the field in either the `CustomInvoiceFormatter`/`CustomInvoiceItemFormatter` class. For example, the `CustomInvoiceFormatter` class defines a field called `greeting` and its getter method.
2. Ensure that you use an invoice template that has this field defined similar to the [Sample HTMLTemplate](https://github.com/killbill/killbill-custom-invoice-formatter-example/blob/main/SampleHTMLTemplate.mustache)

To add custom logic corresponding to an existing field:

Override the corresponding method in the ustomInvoiceFormatter/CustomInvoiceItemFormatter. For example, `CustomInvoiceItemFormatter` [overrides](https://github.com/killbill/killbill-custom-invoice-formatter/blob/d782301f06ef7338e4424c8fc8d55416ca6d8667/src/main/java/org/killbill/billing/plugin/custominvoiceformatter/CustomInvoiceItemFormatter.java#L28C19-L28C38) the `getFormattedEndDate` to return an end date that is one less than the actual end date.
