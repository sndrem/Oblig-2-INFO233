package no.uib.smo015.info233.oblig2.Util;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class RecursiveUtil {

	public static TextNode getFirstTextChild(Node node) {
		if (node instanceof TextNode) {
			return (TextNode) node;
		} else {
			for (Node childNode : node.childNodes()) {
				if (childNode instanceof TextNode) {
					return (TextNode) childNode;
				} else {
					TextNode tn1 = getFirstTextChild(childNode);
					if (tn1 != null) {
						return tn1;
					}
				}
			}
			return null;
		}
	}

}
