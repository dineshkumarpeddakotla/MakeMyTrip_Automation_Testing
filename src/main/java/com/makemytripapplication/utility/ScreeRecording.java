/*
 *Purpose : Class is implemented for recording the screen during the test cases execution
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */
package com.makemytripapplication.utility;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MIME_AVI;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class ScreeRecording extends ScreenRecorder {

    public static ScreenRecorder screenRecorder;
    public String name;

    public ScreeRecording(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                          Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {

        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);

        this.name = name;

    }

    @Override
    protected File createMovieFile(Format fileFormat){

        SimpleDateFormat dateFormat = null;
        try {
            if (!movieFolder.exists()) {
                movieFolder.mkdirs();
            } else if (!movieFolder.isDirectory()) {
                throw new IOException("\"" + movieFolder + "\" is not a directory.");
            }
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(movieFolder,
                name + "-" + Objects.requireNonNull(dateFormat).format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));

    }

    public static void startRecording(String methodName) {
        File file = new File("./recordings/");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();

        try {
            screenRecorder = new ScreeRecording(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, methodName);

            screenRecorder.start();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    public static void stopRecording() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
