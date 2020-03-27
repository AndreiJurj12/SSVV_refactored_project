package marcujurjgr934.repository;


import marcujurjgr934.domain.Grade;
import marcujurjgr934.domain.Pair;
import marcujurjgr934.domain.Student;
import marcujurjgr934.validation.AssignmentValidator;
import marcujurjgr934.validation.StudentValidator;
import marcujurjgr934.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GradeXMLRepository extends AbstractXMLRepository<Pair<String, String>, Grade> {

    public GradeXMLRepository(Validator<Grade> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Grade grade, Document XMLdocument) {
        Element element = XMLdocument.createElement("grade");
        element.setAttribute("IDStudent", grade.getID().getFirst());
        element.setAttribute("IDAssignment", grade.getID().getSecond());

        element.appendChild(createElement(XMLdocument, "GradeValue", String.valueOf(grade.getGradeValue())));
        element.appendChild(createElement(XMLdocument, "DeliveryWeek", String.valueOf(grade.getDeliveryWeek())));
        element.appendChild(createElement(XMLdocument, "Feedback", grade.getFeedback()));

        return element;
    }

    protected Grade getEntityFromNode(Element node) {
        String IDStudent = node.getAttributeNode("IDStudent").getValue();
        String IDAssignment= node.getAttributeNode("IDAssignment").getValue();
        double gradeValue = Double.parseDouble(node.getElementsByTagName("GradeValue").item(0).getTextContent());
        int deliveryWeek = Integer.parseInt(node.getElementsByTagName("DeliveryWeek").item(0).getTextContent());
        String feedback = node.getElementsByTagName("Feedback").item(0).getTextContent();

        return new Grade(new Pair<>(IDStudent, IDAssignment), gradeValue, deliveryWeek, feedback);
    }

    public void createFile(Grade gradeObj) {
        String idStudent = gradeObj.getID().getFirst();
        StudentValidator sval = new StudentValidator();
        AssignmentValidator tval = new AssignmentValidator();
        StudentFileRepository srepo = new StudentFileRepository(sval, "Students.txt");
        AssignmentFileRepository trepo = new AssignmentFileRepository(tval, "Assignments.txt");

        Student student = srepo.findOne(idStudent);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(student.getName() + ".txt", false))) {
            super.findAll().forEach(grade -> {
                if (grade.getID().getFirst().equals(idStudent)) {
                    try {
                        bw.write("Assigment: " + grade.getID().getSecond() + "\n");
                        bw.write("GradeValue: " + grade.getGradeValue() + "\n");
                        bw.write("DeliveryWeek: " + grade.getDeliveryWeek() + "\n");
                        bw.write("Deadline: " + trepo.findOne(grade.getID().getSecond()).getDeadline() + "\n");
                        bw.write("Feedback: " + grade.getFeedback() + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
//    public void createFile(Nota notaObj) {
//        String idStudent = notaObj.getID().getObject1();
//        StudentValidator sval = new StudentValidator();
//        TemaValidator tval = new TemaValidator();
//        StudentXMLRepository srepo = new StudentXMLRepository(sval, "studenti.xml");
//        TemaXMLRepository trepo = new TemaXMLRepository(tval, "teme.xml");
//
//        Student student = srepo.findOne(idStudent);
//        try {
//            Document XMLdocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//            Element root = XMLdocument.createElement("NoteStudent");
//            XMLdocument.appendChild(root);
//
//            super.findAll().forEach(nota -> {
//                if (nota.getID().getObject1().equals(idStudent)) {
//                    try {
//                        Document XMLstudent = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//                        Element element = XMLstudent.createElement("nota");
//
//                        Document XMLdocument2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(trepo.XMLfilename);
//                        Node n = XMLdocument2.getFirstChild();
//                        Node temaNode = XMLstudent.importNode(XMLdocument2, true);
//                        Tema t = trepo.getEntityFromNode((Element) temaNode);
//
//                        element.appendChild(createElement(XMLstudent, "Tema", t.getID()));
//                        element.appendChild(createElement(XMLstudent, "Nota", String.valueOf(nota.getNota())));
//                        element.appendChild(createElement(XMLstudent, "SaptamanaPredare", String.valueOf(nota.getSaptamanaPredare())));
//                        element.appendChild(createElement(XMLstudent, "Deadline", String.valueOf(t.getDeadline())));
//                        element.appendChild(createElement(XMLstudent, "Feedback", nota.getFeedback()));
//
//                        root.appendChild(element);
//
//                    } catch (ParserConfigurationException e) {
//                        e.printStackTrace();
//                    } catch (SAXException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//
//            Transformer XMLtransformer = TransformerFactory.newInstance().newTransformer();
//            XMLtransformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            XMLtransformer.transform(new DOMSource(XMLdocument), new StreamResult(XMLfilename));
//        }
//        catch(ParserConfigurationException pce) {
//            pce.printStackTrace();
//        }
//        catch(TransformerConfigurationException tce) {
//            tce.printStackTrace();
//        }
//        catch(TransformerException te) {
//            te.printStackTrace();
//        }
//    }}
