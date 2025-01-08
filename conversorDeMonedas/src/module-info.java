
module conversor_de_monedas {
    requires java.net.http;
    requires com.google.gson;
    opens com.myproyect.conversor.data to com.google.gson;  
}
