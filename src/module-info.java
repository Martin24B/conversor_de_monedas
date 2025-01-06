
module conversor_de_monedas {
    requires java.net.http;
    requires com.google.gson;
    opens data to com.google.gson;  
}
