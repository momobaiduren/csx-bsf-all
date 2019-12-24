package com.yh.csx.bsf.message.dingding;

import com.yh.csx.bsf.core.http.DefaultHttpClient;
import com.yh.csx.bsf.core.http.HttpClient;
import lombok.Data;
import org.apache.http.entity.ContentType;
import org.springframework.util.StringUtils;
import java.net.SocketTimeoutException;


/**
 * @author: chejiangyi
 * @version: 2019-07-23 13:49
 **/
@Data
public class DingdingProvider {

    private String getUrl()
    {
        return StringUtils.trimTrailingCharacter(DingdingProperties.Domain,'/')+"/robot/send?access_token={access_token}";
    }


    public  void send(String[] tokens, DingdingBody content)  {
        HttpClient.Params params = HttpClient.Params.custom().setContentType(ContentType.APPLICATION_JSON).add(content).build();
        sendToken(tokens,params);
    }

    public void sendMarkDown(String[] tokens,String subject,String text){
        DingdingBody.MarkDown markDown = new DingdingBody.MarkDown();
        markDown.setText(text);markDown.setTitle(subject);
        DingdingBody dingdingBody = new DingdingBody();
        dingdingBody.setMarkdown(markDown);
        dingdingBody.setMsgtype("markdown");
        send(tokens,dingdingBody);
    }

    public void sendText(String[] tokens,String subject,String text){
        DingdingBody.Text text1 = new DingdingBody.Text();
        text1.setContent(text);
        DingdingBody dingdingBody = new DingdingBody();
        dingdingBody.setText(text1);
        dingdingBody.setMsgtype("text");
        send(tokens,dingdingBody);
    }

    private void sendToken(String[] tokens,HttpClient.Params params)
    {
        if(tokens!=null) {
            for (String token : tokens) {
                try {
                    DefaultHttpClient.Default.post(getUrl().replace("{access_token}", token), params);
                }catch (Exception e){
                    if(e.getCause() instanceof SocketTimeoutException)
                    {
                        //钉钉网关不一定稳定
                        return;
                    }
                    throw e;
                }
            }
        }
    }

    @Data
    public static class DingdingBody
    {
        @Data
        public static class MarkDown
        {
            private String title;
            private String text;
        }
        @Data
        public static class Text
        {
            private String content;
        }
        /**
         * "markdown","text"
         */
        private String msgtype="markdown";
        private MarkDown markdown;
        private Text text;
    }
}
