package ru.orus.hw12.html.page.create;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class HtmlCreator {
    private static final String HTML_DIR = "web_resources";
    private static HtmlCreator instance = new HtmlCreator();

    private final Configuration configuration;

    private HtmlCreator() {
        configuration = new Configuration();
    }

    public static HtmlCreator instance() {
        return instance;
    }

    public String create(String filename, Map<String, Object> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }
}
