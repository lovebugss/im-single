package com.itrjp.im.message.service.filter;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/12/30 23:39
 */
public class MessageFilterFactory {

    public static MessageFilter create(MessageFilterType type) {
        return switch (type) {
            case AUTO -> new AutoMessageFilter();
            case MANUAL -> new ManualMessageFilter();
            case BLACK_LIST -> new BlackListMessageFilter();
            case WHITE_LIST -> new WhiteListMessageFilter();
        };
    }
}
