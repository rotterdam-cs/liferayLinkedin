package com.aimprosoft.portlet.login;

import com.aimprosoft.portlet.login.model.LoginPreferences;
import com.aimprosoft.portlet.login.util.LoginPreferencesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderRequest;

@Controller
@RequestMapping("EDIT")
@SessionAttributes("messages")
public class LoginEditController {

    //private ApplicationPropsBean props = ObjectFactory.getBean(ApplicationPropsBean.class);

    /*ublic ApplicationPropsBean getProps() {
        return props;
    }

    public void setProps(ApplicationPropsBean props) {
        this.props = props;
    }*/

    @RequestMapping
    public Object view(RenderRequest renderRequest) {

        ModelMap modelMap = new ModelMap();

        LoginPreferences loginPreferences = LoginPreferencesUtil.getPreferences(renderRequest);

        modelMap.put("preferences", loginPreferences);

        return new ModelAndView("/login/edit/view", modelMap);
    }

    @RenderMapping(params = "action=savePrefs")
    public ModelAndView save(@ModelAttribute LoginPreferences loginPreferences,
                             RenderRequest request) {


        ModelMap modelMap = new ModelMap();

        LoginPreferencesUtil.storePreferences(request, loginPreferences);
        modelMap.put("successMsg", "Your request processed successfully!");

        modelMap.put("preferences", loginPreferences);

        return new ModelAndView("/login/view/index", modelMap);
    }

}
