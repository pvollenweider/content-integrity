package org.jahia.modules.contentintegrity.actions;

import org.jahia.api.Constants;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.modules.contentintegrity.services.Utils;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class CheckIntegrityAction extends Action {

    private static final Logger logger = LoggerFactory.getLogger(CheckIntegrityAction.class);

    @Override
    public ActionResult doExecute(HttpServletRequest req, RenderContext renderContext, Resource resource, JCRSessionWrapper session, Map<String, List<String>> parameters, URLResolver urlResolver) throws Exception {
        final String action = getParameter(parameters, "action");
        switch (action) {
            case "print":
                Utils.getContentIntegrityService().printIntegrityChecksList(true);
                break;
            case "check":
                final String ws = getParameter(parameters, "workspace", Constants.EDIT_WORKSPACE);
                Utils.getContentIntegrityService().validateIntegrity(resource.getNodePath(), ws);
        }
        return null;
    }
}
