package com.szzt.iot.common.netty;

import com.google.protobuf.MessageLite;
import com.szzt.iot.common.constant.SysConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 返回消息处理(IMProtoMessage to protobuf) 
 *
 * @author zhouhongjin
 */
public final class PacketEncoder extends MessageToByteEncoder<IMProtoMessage<MessageLite>> {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    protected void encode(final ChannelHandlerContext ctx, final IMProtoMessage<MessageLite> protoMessage, final ByteBuf out) throws Exception {

        try {
            logger.trace("Protobuf encode started.");
            
            // [HEADER] data
            IMHeader header = protoMessage.getHeader();
            
            byte[] bytes = protoMessage.getBody().toByteArray();
            int length = bytes.length;

            // Set the length of bytebuf
            header.setLength(SysConstant.PROTOCOL_HEADER_LENGTH + length);
            
            byte[] allbytes = header.encode().array();
            allbytes = Arrays.copyOf(allbytes, SysConstant.PROTOCOL_HEADER_LENGTH + length);
            
            for (int i = 0; i < length; i++) {
                allbytes[i + 16] = bytes[i];
            }
            
            out.writeBytes(allbytes);
            logger.trace("Sent protobuf: commandId={}", header.getCommandId());
        } catch (Exception e) {
            logger.error("编码异常", e);
        } finally {
            logger.trace("Protobuf encode finished.");
        }
    }
}
