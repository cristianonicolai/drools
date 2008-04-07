package org.drools.xml.processes;

import org.drools.process.core.timer.Timer;
import org.drools.workflow.core.Node;
import org.drools.workflow.core.node.TimerNode;
import org.drools.xml.Configuration;
import org.drools.xml.ExtensibleXmlParser;
import org.xml.sax.SAXException;

public class TimerNodeHandler extends AbstractNodeHandler {

    protected Node createNode() {
        return new TimerNode();
    }

    public void handleNode(final Node node, final Configuration config, final String uri,
            final String localName, final ExtensibleXmlParser parser)
            throws SAXException {
        super.handleNode(node, config, uri, localName, parser);
        TimerNode timerNode = (TimerNode) node;
        String delay = config.getAttribute("delay");
        String period = config.getAttribute("period");
        if (delay != null || period != null) {
            Timer timer = timerNode.getTimer();
            if (timer == null) {
                timer = new Timer();
                timerNode.setTimer(timer);
            }
            if (delay != null) {
                timer.setDelay(new Long(delay));
            }
            if (period != null) {
                timer.setPeriod(new Long(period));
            }
        }
    }

    public Class generateNodeFor() {
        return TimerNode.class;
    }

}