package com.rainarmy.tools.base;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * HtmlTagUtil
 *
 * @author wangchengjun
 * @version V1.0
 * @date 2021/10/29 9:36
 */
public class HtmlTagUtil {
    /** js标签 */
    private static final String REG_EX_SCRIPT = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
    /** css标签 */
    private static final String REG_EX_STYLE = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";

    /** 特殊嵌套标签需要优先去掉 */
    private static final String REG_EX_HTML_SP_START = "<d class=[^>]+>";

    /** 特殊嵌套标签需要优先去掉 */
    private static final String REG_EX_HTML_SP_END = "</d>";

    /** html标签 */
    private static final String REG_EX_HTML = "<[^>]+>";
    /** {@code <blockquote>标签} */
    private static final String REG_EX_HTML_BLOCKQUOTE = "</?blockquote>";
    /** 其他html转义符 */
    private static final String REG_EX_SPECIAL = "&[a-zA-Z]{1,10};";


    /**
     * 删除html标签
     * @param srcStr 原正文内容
     * @return 过滤后的正文内容
     */
    public static String removeHtmlTag(String srcStr) {
        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY : unescape(srcStr)
                .replaceAll(REG_EX_SCRIPT, "").replaceAll(REG_EX_STYLE, "")
                .replaceAll(REG_EX_HTML, "").replaceAll(REG_EX_SPECIAL, "");
    }

    /**
     * 删除HTML标签并格式化
     * <li>去除特殊空格、全角空格、换行符、制表符、空格</li>
     * <li>去除多余空行</li>
     * <li>首行缩进，换行缩进</li>
     * <li>去除js标签、html标签</li>
     * @param srcStr 原始资讯正文内容（带HTML格式）
     * @return 删除HTML标签后的正文内容
     */
    public static String removeHtmlAndFormat(String srcStr) {
        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY : unescape(srcStr)
                // 去除特殊空格
                .replaceAll("\\u00A0", "")
                // 去除全角空格
                .replaceAll("\\u3000", "")
                // 去除换行符、制表符
                .replaceAll("[\\t\\n\\r]", "")
                // 删除首行空格
                .replaceFirst("\\s+", "")
                // 删除2个以上的空格
                .replaceAll("\\s{2,}", "")
                // 首行缩进
                .replaceFirst("", "        ")
                // 去掉图片标签，排除干扰
                .replaceAll("<(?i)img[^>]+>", "")
                // 去除多余空行
                .replaceAll("<(?i)p>\\s*</(?i)p>", "")
                // 换行缩进（p标签）
                .replaceAll("<(?i)p>", "<p>\r\n        ")
                // 换行缩进（p标签）
                .replaceAll("</(?i)p>", "<p>\r\n        ")
                // 换行缩进（br标签）
                .replaceAll("</?(?i)br\\s?/?>", "\r\n        ")
                // 换行缩进（tr标签）
                .replaceAll("</(?i)tr>", "\r\n        ")
                // 空格（td标签）
                .replaceAll("</(?i)td>", " ")
                // 换行缩进（section标签）
                .replaceAll("</(?i)section>", "\r\n        ")
                // 特殊嵌套标签需要优先去掉
                .replaceAll(REG_EX_HTML_SP_START, "").replaceAll(REG_EX_HTML_SP_END, "")
                // 去除js标签
                .replaceAll(REG_EX_SCRIPT, "").replaceAll(REG_EX_STYLE, "")
                // 去除html标签
                .replaceAll(REG_EX_HTML, "").replaceAll(REG_EX_SPECIAL, "")
                // 去除多余空行
                .replaceAll("\r\n\\s+\r\n", "\r\n");
    }

    /**
     * HTML内容进行HTML标签转义
     * <li>HTML标签转义举例：{@code &lt;p&gt; -> <p>}</li>
     * @param srcStr 原始内容
     * @return 转码后的HTML内容
     */
    public static String unescape(String srcStr) {
        if (StringUtils.isBlank(srcStr)) {
            return StringUtils.EMPTY;
        }
        return StringEscapeUtils.unescapeHtml4(srcStr);
    }
}
