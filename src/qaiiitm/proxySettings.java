/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qaiiitm;

/**
 *
 * @author FoUkat
 */
public class proxySettings {

    private String user = "aasf";
    private String password = "@@sfv5a216";
    private String enabled = "true";
    private String host = "192.168.1.103";
    private String port = "3128";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public void setProxy(String enabledf,String hostf,String portf,String userf,String passf)
    {
        enabled = enabledf;
        host = hostf;
        port = portf;
        user = userf;
        password = passf;
    }
    public String getEnabled()
    {
        return enabled;
    }
    public String getHost()
    {
        return host;
    }
    public String getPort()
    {
        return port;
    }
    public String getUser()
    {
        return user;
    }
    public String getPass()
    {
        return password;
    }

}
