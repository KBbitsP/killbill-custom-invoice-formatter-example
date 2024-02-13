# killbill-custom-invoice-formatter

Custom invoice formatter that can be used to customize HTML invoices.

## Build

```
mvn clean install -DskipTests=true 
```

## Installation

Run the following [kpm](https://github.com/killbill/killbill-cloud/blob/master/kpm) command:

```
kpm install_java_plugin 'custom-invoice-formatter' --from-source-file=<jar_file_path> --destination=<path_to_install_plugin>
```

