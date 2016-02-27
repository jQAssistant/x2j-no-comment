package com.buschmais.x2j.nocomment;

import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.util.Map;

/**
 * Removes all Javadoc from all classes.
 *
 * @author <a href="mailto:o.b.fischer@swe-blog.net">Oliver B. Fischer</a>
 */
public class NoJavadocCommentsPlugin extends Plugin {
    private static final String OPTION_NAME = "Xno-comment";

    @Override
    public String getOptionName() {
        return OPTION_NAME;
    }

    @Override
    public String getUsage() {
        return "   -" + OPTION_NAME + "         :  removes all Javadoc from the generated classes";
    }

    @Override
    public boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException {

        for (ClassOutline classOutline : outline.getClasses()) {
            classOutline.implClass.javadoc().clear();

            for (JMethod method : classOutline.implClass.methods()) {
                method.javadoc().clear();
            }

            for (Map.Entry<String, JFieldVar> fieldVarEntry : classOutline.implClass.fields().entrySet()) {
                fieldVarEntry.getValue().javadoc().clear();
            }
         }

        return true;
    }
}
