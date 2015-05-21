/**
 * 
 */
package com.ai.webservice.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Class Name		: SpringUsersWsClient<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public class SpringUsersWsClient {

    /**Description	： 这里记述method的说明<br>
     * 
     * liuhb
     * @param args
     * @since
     * 
     */
    public static void main(String[] args) {
        //调用WebService
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(UserService.class);
        factory.setAddress("http://127.0.0.1/services/UserService");
        UserService service = (UserService) factory.create();
        User user = service.getUserByName("hoojo");
        System.out.println(user.getEmail());
        user.setAddress("China-Guangzhou");
        service.setUser(user);
    }
}
