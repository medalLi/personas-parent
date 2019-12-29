package com.desheng.bigdata.personas.common.device;

public class EmailUtils {
    /**
     *   网易邮箱 @163.com @126.com
         移动邮箱 @139.com
         搜狐邮箱 @sohu.com
         qq邮箱  @qq.com
         189邮箱 @189.cn
         tom邮箱 @tom.com
         阿里邮箱 @aliyun.com
         新浪邮箱 @sina.com
         等等
     * @param email
     * @return
     */
    public static String getOperatorByEmail(String email){
        String emailType = "其他邮箱";
        if(email.contains("@163.com")||email.contains("@126.com")){
            emailType = "网易邮箱";
        } else if (email.contains("@139.com")){
            emailType = "移动邮箱";
        } else if (email.contains("@sohu.com")){
            emailType = "搜狐邮箱";
        } else if (email.contains("@qq.com")){
            emailType = "qq邮箱";
        } else if (email.contains("@189.cn")){
            emailType = "189邮箱";
        } else if (email.contains("@aliyun.com")){
            emailType = "阿里邮箱";
        } else if (email.contains("@sina.com")){
            emailType = "新浪邮箱";
        }
        return emailType;
    }
}
