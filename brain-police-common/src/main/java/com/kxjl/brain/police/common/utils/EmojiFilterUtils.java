package com.kxjl.brain.police.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /**
  * 过滤微信昵称特殊字符
  * @Author DengWei
  * @Date 2017/5/25 15:34
  */
public class EmojiFilterUtils {
	
	
	public static String filterEmoji(String source) {
		if (source != null) {
			Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emoji.matcher(source);
			if (emojiMatcher.find()) {
				source = emojiMatcher.replaceAll("");
				return source;
			}
			return source;
		}
		return source;
	}
}
