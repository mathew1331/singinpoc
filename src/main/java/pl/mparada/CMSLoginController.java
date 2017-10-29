package pl.mparada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CMSLoginController {

    // simulates logic of commerce(BLC) of login user
    @Autowired
    private CommerceLoginService commerceLoginService;

    @GetMapping("loginpage")
    public String loginpage(Model model) {
        return "login";
    }

    @PostMapping("logincallback")
    public @ResponseBody User logincallback(@RequestParam("idtoken") String token, @RequestParam("sso_type") String ssoType, Model model) throws Exception {
        // sample token for google eyJhbGciOiJSUzI1NiIsImtpZCI6ImY0MGYxYTVmNGQ0OWVmOGY3YTI3ZjQ5NThhOTZkYjgzNWRiY2M0MmMifQ.eyJhenAiOiIxMTQyMTM0Njc1MTQtNXVvYzRqZjd1ZzEyN3E5cDNlbGxvOGtzcW81dmQyMnMuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxMTQyMTM0Njc1MTQtNXVvYzRqZjd1ZzEyN3E5cDNlbGxvOGtzcW81dmQyMnMuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDEwOTgwMDY2NzAxNjQ1NzMwODUiLCJlbWFpbCI6Im1hdGhldzEzMzFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJnT0FpLWdDbzhiVE84ckFvV1E1N0xnIiwiaXNzIjoiYWNjb3VudHMuZ29vZ2xlLmNvbSIsImp0aSI6ImU5YjZhNDJhM2MyMWZmZmU5MzM4NGNhMmI3NDc3M2RlMDQzZTY3N2UiLCJpYXQiOjE1MDkzMDAzNDksImV4cCI6MTUwOTMwMzk0OSwibmFtZSI6Ik1hdGV1c3ogUGFyYWRhIiwicGljdHVyZSI6Imh0dHBzOi8vbGg1Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8tLUtqSVQ1OUlxSTAvQUFBQUFBQUFBQUkvQUFBQUFBQUFBN2svVjB6UGFFV2otY0kvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6Ik1hdGV1c3oiLCJmYW1pbHlfbmFtZSI6IlBhcmFkYSIsImxvY2FsZSI6InBsIn0.Y8oI3BQJYSjKkPWCd8YXa73Cm0b68ZSK-ROcj6CxiOlfWS7CSqtIFk7LcEkR5o1L6zR8aVFdhs4GlKhYBP0DvQ5R8ftGJF-N3xgobxAbma-URlpyG_qqCOaw8kyLmnbk9zCNmImUvgp6aA5BOE4fZPXdwmNWi039RiBNcntCvc7P_g_kVu8c1haGDUebfO_2UKzHplOlRuzM0VCCR1H2cpWo0CahJu4OAPDqJM5PCq6DetGC42aDC1XC7IPE9lLbPRg_iGOzPv8T5XnfzzcXrW8my7_ZrCWLnmuaPIGfePgwEf1Q6rRgwH03C9LTgC_e2bis90Kj7kqA23ZI9gXbFg
        System.out.println("sso type: " + ssoType + " token: " + token);

        if (ssoType.equals("google")) {
            // simulates call to commerce via api gateway
            return commerceLoginService.loginGoogleUser(token);
        }
        return null;
    }
}
