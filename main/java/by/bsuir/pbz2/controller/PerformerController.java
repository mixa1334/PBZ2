package by.bsuir.pbz2.controller;

import by.bsuir.pbz2.model.entity.Performer;
import by.bsuir.pbz2.model.exception.CustomException;
import by.bsuir.pbz2.model.repository.performer.PerformerRepository;
import by.bsuir.pbz2.model.repository.specification.performer.AllPerformers;
import by.bsuir.pbz2.view.PerformerWindow;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformerController {
    private final String ID_REGEX = "\\d+";
    private final PerformerRepository repository;
    private final PerformerWindow window;
    private Map<Integer, Performer> performerMap;

    public PerformerController(PerformerRepository repository, PerformerWindow window) {
        this.repository = repository;
        this.window = window;

        window.setActionToUpdateButton(a -> updateData(true));
        window.getAddPerformerDialog().setButtonAction(a -> addPerformer());
        window.getDeletePerformerDialog().setButtonAction(a -> deletePerformer());
        window.getEditPerformerDialog().setButtonAction(a -> editPerformer());
        updateData(false);
    }

    public Map<Integer, Performer> getPerformerMap() {
        return performerMap;
    }

    private void updateData(boolean showMsg) {
        try {
            List<Performer> performersFromDb = repository.findEntity(new AllPerformers());
            performerMap = new HashMap<>();
            performersFromDb.forEach(p -> performerMap.put(p.getPerformerId(), p));
            window.getPerformerViewer().setEntityToDisplay(performersFromDb);
            if (showMsg) {
                JOptionPane.showMessageDialog(window, "successfully updated!");
            }
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void deletePerformer() {
        try {
            String result = window.getDeletePerformerDialog().getIdToDelete();
            window.getDeletePerformerDialog().clearTextFields();
            if (!result.matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid id: " + result);
                return;
            }
            int id = Integer.parseInt(result);
            if (!performerMap.containsKey(id)) {
                JOptionPane.showMessageDialog(window, "invalid id to delete: " + id);
                return;
            }

            Performer performer = performerMap.get(id);
            repository.deleteEntity(performer);
            performerMap.remove(id);

            deletePerformerFromTable(performer);

            JOptionPane.showMessageDialog(window, "successfully deleted!");
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void addPerformer() {
        try {
            String[] results = window.getAddPerformerDialog().getValuesFromUser();
            if (!results[0].matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid id: " + results[0]);
                return;
            }
            int id = Integer.parseInt(results[0]);
            if (performerMap.containsKey(id)) {
                JOptionPane.showMessageDialog(window, "id already exists: " + id);
                return;
            }

            Performer performer = new Performer();
            performer.setPerformerId(id);
            performer.setPosition(results[1]);
            performer.setSubdivision(results[2]);
            performer.setFio(results[3]);

            repository.createEntity(performer);
            performerMap.put(id, performer);

            addPerformerToTable(performer);
            window.getAddPerformerDialog().clearTextFields();

            JOptionPane.showMessageDialog(window, "successfully added!");
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void editPerformer() {
        try {
            String[] results = window.getEditPerformerDialog().getValuesFromUser();
            if (!results[0].matches(ID_REGEX)) {
                JOptionPane.showMessageDialog(window, "invalid id: " + results[0]);
                return;
            }
            int id = Integer.parseInt(results[0]);
            if (!performerMap.containsKey(id)) {
                JOptionPane.showMessageDialog(window, "id does not exists: " + id);
                return;
            }

            Performer performer = new Performer();
            performer.setPerformerId(id);
            performer.setPosition(results[1]);
            performer.setSubdivision(results[2]);
            performer.setFio(results[3]);

            repository.updateEntity(performer);
            Performer old = performerMap.replace(id, performer);
            deletePerformerFromTable(old);
            addPerformerToTable(performer);
            window.getEditPerformerDialog().clearTextFields();

            JOptionPane.showMessageDialog(window, "successfully changed!");
        } catch (CustomException e) {
            JOptionPane.showMessageDialog(window, "Smth wrong with DB");
        }
    }

    private void deletePerformerFromTable(Performer performer) {
        window.getPerformerViewer().deleteEntityFromDisplay(performer);
    }

    private void addPerformerToTable(Performer performer) {
        window.getPerformerViewer().addEntityToDisplay(performer);
    }
}
