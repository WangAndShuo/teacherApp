package com.classproject.teacherapp.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zou_cl@suixingpay.com
 * @Date:
 * @Version: 1.0
 */
@Slf4j
public class ImageUtils {
    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    private static final int WIDTH = 582; // 二维码宽
    private static final int HEIGHT = 582; // 二维码高

    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;

        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8"); // 设置编码方式
            put(EncodeHintType.MARGIN, 0);
        }
    };

    // 生成带logo的二维码图片
    private static BufferedImage drawLogoQRCode(String qrUrl) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }

  //          int width = image.getWidth();
 //           int height = image.getHeight();
//            if (Objects.nonNull(logoFile) && logoFile.exists()) {
//                // 构建绘图对象
//                Graphics2D g = image.createGraphics();
//                // 读取Logo图片
//                BufferedImage logo = ImageIO.read(logoFile);
//                // 开始绘制logo图片
//                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
//                g.dispose();
//                logo.flush();
//            }

            image.flush();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static BufferedImage mergeImage(BufferedImage small, String x, String y, String qrNum) {
        if (small == null || StringUtils.isBlank(qrNum)) {
            return null;
        }
        Font font = new Font("微软雅黑", Font.PLAIN, 32);
        try {
            ClassPathResource cpr = new ClassPathResource("/static/image/img_qrcode_bg1.png");
            BufferedImage big = ImageIO.read(cpr.getInputStream());
            Graphics2D g = big.createGraphics();
            int fw = getWordWidth(font, qrNum);
            int f = big.getWidth() - fw;
            float fx = Float.parseFloat(x);
            float fy = Float.parseFloat(y);
            int x_i = (int) fx;
            int y_i = (int) fy;
            g.drawImage(small, x_i, y_i, small.getWidth(), small.getHeight(), null);
            g.setFont(font);
            g.setColor(new Color(102, 102, 102));
            g.drawString(qrNum, f / 2, 1103);
            g.dispose();
            return big;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    public static void creatQrImge(String fileName, String qrUrl, String qrNum, HttpServletResponse response) {
        try {
            BufferedImage small = drawLogoQRCode(qrUrl);
            BufferedImage big = mergeImage(small, "394", "459", qrNum);
            if (big != null) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;" + "fileName=" + fileName);
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
                ImageIO.write(big, "png", imOut);
                InputStream fileInputStream = new ByteArrayInputStream(bs.toByteArray());
                BufferedInputStream bufferedInputStream = null;
                try {
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    OutputStream outputStream = response.getOutputStream();
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                } catch (IOException e) {
                    log.info("生成台卡异常2", e);
                } finally {
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e) {
                            log.info("生成台卡异常3", e);
                        }
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        log.info("生成台卡异常4", e);
                    }
                }
            }
        } catch (IOException e) {
            log.info("生成台卡异常1", e);
        }
    }

}
