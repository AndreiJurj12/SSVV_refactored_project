package marcujurjgr934.repository;


import marcujurjgr934.domain.Assignment;
import marcujurjgr934.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AssignmentXMLRepository extends AbstractXMLRepository<String, Assignment> {

    public AssignmentXMLRepository(Validator<Assignment> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Assignment assignment, Document XMLdocument) {
        Element element = XMLdocument.createElement("assignment");
        element.setAttribute("ID", assignment.getID());

        element.appendChild(createElement(XMLdocument, "Description", assignment.getDescription()));
        element.appendChild(createElement(XMLdocument, "Deadline", String.valueOf(assignment.getDeadline())));
        element.appendChild(createElement(XMLdocument, "Startline", String.valueOf(assignment.getStartline())));

        return element;
    }

    protected Assignment getEntityFromNode(Element node) {
        String ID = node.getAttributeNode("ID").getValue();
        String description = node.getElementsByTagName("Description").item(0).getTextContent();
        int deadline = Integer.parseInt(node.getElementsByTagName("Deadline").item(0).getTextContent());
        int startline = Integer.parseInt(node.getElementsByTagName("Startline").item(0).getTextContent());

        return new Assignment(ID, description, deadline, startline);
    }
}
