import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

public class PicProcessing {
    private int targetLength = 55; //小图长
    private int targetWidth = 45; //小图宽
    private double circleR = 8;   //半径
    private double r1 = 4;       //距离点
    private double d1,d2;

    //先确定一个抠出图的轮廓
    private int[][] getBlockData() {
        int[][] data = new int[targetLength][targetWidth];
        double x2 = targetLength-circleR-2;
        //随机生成圆的位置
        double h1 = circleR + Math.random() * (targetWidth-3*circleR-r1);
        double po = circleR*circleR;  //64

        double xbegin = targetLength-circleR-r1;
        double ybegin = targetWidth-circleR-r1;

        //圆的标准方程 (x-a)²+(y-b)²=r²,标识圆心（a,b）,半径为r的圆
        //计算需要的小图轮廓，用二维数组来表示，二维数组有两张值，0和1，其中0表示没有颜色，1有颜色
        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                double d2 = Math.pow(j - 2,2) + Math.pow(i - h1,2);
                double d3 = Math.pow(i - x2,2) + Math.pow(j - h1,2);

                if ((j <= ybegin && d2 < po) || (i >= xbegin && d3 > po)) {
                    data[i][j] = 0;
                }  else {
                    data[i][j] = 1;
                }
            }
        }
        return data;
    }

    //有这个轮廓后就可以依据这个二维数组的值来判定抠图并在原图上抠图位置处加阴影
    private void cutByTemplate(BufferedImage oriImage, BufferedImage targetImage, int[][] templateImage, int x, int y){
        int[][] martrix = new int[3][3];
        int[] values = new int[9];

        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                int rgb = templateImage[i][j];
                // 原图中对应位置变色处理

                int rgb_ori = oriImage.getRGB(x + i, y + j);

                if (rgb == 1) {
                    //抠图上复制对应颜色值
                    targetImage.setRGB(i, y + j, rgb_ori);
                    //抠图区域高斯模糊
                    readPixel(oriImage, x + i, y + j, values);
                    fillMatrix(martrix, values);
                    oriImage.setRGB(x + i, y + j, avgMatrix(martrix));
                }else{
                    //这里把背景设为透明
                    targetImage.setRGB(i, j, rgb_ori & 0x00ffffff);
                }
            }
        }
    }

    private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {
        int xStart = x - 1;
        int yStart = y - 1;
        int current = 0;
        for (int i = xStart; i < 3 + xStart; i++)
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;

                } else if (tx >= img.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                } else if (ty >= img.getHeight()) {
                    ty = y;
                }
                pixels[current++] = img.getRGB(tx, ty);

            }
    }

    private static void fillMatrix(int[][] matrix, int[] values) {
        int filled = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();
    }


    /**
     * @Description: 读取本地图片，生成拼图验证码
     * @author zhoujin
     * @return Map<String,Object>  返回生成的抠图和带抠图阴影的大图 base64码及抠图坐标

    public static Map<String,Object> createImage(File file, Map<String,Object> resultMap){
        try {
            BufferedImage oriImage = ImageIO.read(file);
            Random random = new Random();
            //X轴距离右端targetWidth  Y轴距离底部targetHeight以上
            int widthRandom = random.nextInt(oriImage.getWidth()-  2*targetWidth) + targetWidth;
            int heightRandom = random.nextInt(oriImage.getHeight()- targetHeight);
            logger.info("原图大小{} x {},随机生成的坐标 X,Y 为（{}，{}）",oriImage.getWidth(),oriImage.getHeight(),widthRandom,heightRandom);

            BufferedImage targetImage= new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR);
            cutByTemplate(oriImage,targetImage,getBlockData(),widthRandom,heightRandom);

            resultMap.put("bigImage", getImageBASE64(oriImage));//大图
            resultMap.put("smallImage", getImageBASE64(targetImage));//小图
            resultMap.put("xWidth",widthRandom);
            resultMap.put("yHeight",heightRandom);
        } catch (Exception e) {
            logger.info("创建图形验证码异常",e);
        } finally{
            return resultMap;
        }
    }*/


        /**
         * @Description: 读取网络图片，生成拼图验证码
         * @author zhoujin
         * @return Map<String,Object>  返回生成的抠图和带抠图阴影的大图 base64码及抠图坐标

   public static Map<String,Object> createImage(String imgUrl, Map<String,Object> resultMap){
        try {
            //通过URL 读取图片
            URL url = new URL(imgUrl);
            BufferedImage bufferedImage = ImageIO.read(url.openStream());
            Random rand = new Random();
            int widthRandom = rand.nextInt(bufferedImage.getWidth()-  targetWidth - 100 + 1 ) + 100;
            int heightRandom = rand.nextInt(bufferedImage.getHeight()- targetHeight + 1 );
            logger.info("原图大小{} x {},随机生成的坐标 X,Y 为（{}，{}）",bufferedImage.getWidth(),bufferedImage.getHeight(),widthRandom,heightRandom);

            BufferedImage target= new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR);
            cutByTemplate(bufferedImage,target,getBlockData(),widthRandom,heightRandom);
            resultMap.put("bigImage", getImageBASE64(bufferedImage));//大图
            resultMap.put("smallImage", getImageBASE64(target));//小图
            resultMap.put("xWidth",widthRandom);
            resultMap.put("yHeight",heightRandom);
        } catch (Exception e) {
            logger.info("创建图形验证码异常",e);
        } finally{
            return resultMap;
        }
    }*/


    /**
     * @Title: getImageBASE64
     * @Description: 图片转BASE64
     * @author zhoujin
     * @param image
     * @return
     * @throws IOException String

    public static String getImageBASE64(BufferedImage image) throws IOException {
        byte[] imagedata = null;
        ByteArrayOutputStream bao=new ByteArrayOutputStream();
        ImageIO.write(image,"png",bao);
        imagedata=bao.toByteArray();
        BASE64Encoder encoder = Base64.getEncoder();
        String BASE64IMAGE=encoder.encodeBuffer(imagedata).trim();
        BASE64IMAGE = BASE64IMAGE.replaceAll("\r|\n", "");  //删除 \r\n
        return BASE64IMAGE;
    }*/

    /**
     * @param @return 参数说明
     * @return BaseRestResult 返回类型
     * @Description: 生成滑块拼图验证码

    @RequestMapping(value = "/getImageVerifyCode.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public BaseRestResult getImageVerifyCode() {
        Map<String, Object> resultMap = new HashMap<>();
        //读取本地路径下的图片,随机选一条
        File file = new File(this.getClass().getResource("/image").getPath());
        File[] files = file.listFiles();
        int n = new Random().nextInt(files.length);
        File imageUrl = files[n];
        ImageUtil.createImage(imageUrl, resultMap);

        //读取网络图片
        //ImageUtil.createImage("http://hbimg.b0.upaiyun.com/7986d66f29bfeb6015aaaec33d33fcd1d875ca16316f-2bMHNG_fw658",resultMap);
        session.setAttribute("xWidth", resultMap.get("xWidth"));
        resultMap.remove("xWidth");
        resultMap.put("errcode", 0);
        resultMap.put("errmsg", "success");
        return new BaseRestResult(resultMap);
    }*/


    /**
     * 校验滑块拼图验证码
     *
     * @param moveLength 移动距离
     * @return BaseRestResult 返回类型
     * @Description: 生成图形验证码

//    @RequestMapping(value = "/verifyImageCode.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public BaseRestResult verifyImageCode(@RequestParam(value = "moveLength") String moveLength) {
        Double dMoveLength = Double.valueOf(moveLength);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Integer xWidth = (Integer) session.getAttribute("xWidth");
            if (xWidth == null) {
                resultMap.put("errcode", 1);
                resultMap.put("errmsg", "验证过期，请重试");
                return new BaseRestResult(resultMap);
            }
            if (Math.abs(xWidth - dMoveLength) > 10) {
                resultMap.put("errcode", 1);
                resultMap.put("errmsg", "验证不通过");
            } else {
                resultMap.put("errcode", 0);
                resultMap.put("errmsg", "验证通过");
            }
        } catch (Exception e) {
            throw new EsServiceException(e.getMessage());
        } finally {
            session.removeAttribute("xWidth");
        }
        return new BaseRestResult(resultMap);
    }*/

    /*一对图片做模糊处理增加机器识别难度，二做适当同质量压缩
    public static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }

        int size = radius * 2 + 1;
        float[] data = new float[size];

        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;

        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }

        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }

        Kernel kernel = null;
        if (horizontal) {
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }

    public static void simpleBlur(BufferedImage src,BufferedImage dest) {
        BufferedImageOp op = getGaussianBlurFilter(2,false);
        op.filter(src, dest);
    }

    //同质压缩
    public static byte[] fromBufferedImage2(BufferedImage img,String imagType) throws IOException {
        bos.reset();
        // 得到指定Format图片的writer
         Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(imagType);
         ImageWriter writer = (ImageWriter) iter.next();
        // 得到指定writer的输出参数设置(ImageWriteParam )
         ImageWriteParam iwp = writer.getDefaultWriteParam();
         iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
         iwp.setCompressionQuality(1f); // 设置压缩质量参数

         iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

         ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
        colorModel.createCompatibleSampleModel(16, 16)));

//        writer.setOutput(ImageIO.createImageOutputStream(bos));
        IIOImage iIamge = new IIOImage(img, null, null);
        writer.write(null, iIamge, iwp);
//        byte[] d = bos.toByteArray();
        return d;
        }
        */
}
