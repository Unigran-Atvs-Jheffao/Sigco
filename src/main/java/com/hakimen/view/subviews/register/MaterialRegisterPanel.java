package com.hakimen.view.subviews.register;

import com.hakimen.controllers.EmployeeController;
import com.hakimen.controllers.MaterialController;
import com.hakimen.controllers.dto.EmployeeDTO;
import com.hakimen.controllers.dto.MaterialDTO;
import com.hakimen.exceptions.InvalidValueException;
import com.hakimen.utils.ViewUtils;
import com.hakimen.view.View;
import com.hakimen.view.components.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class MaterialRegisterPanel extends RegisterPanel<MaterialDTO> implements View {

    private JTextField materialName;
    private JSpinner materialQuantity;
    private JSpinner minMaterialQuantity;
    private JTextField managerName;

    public MaterialRegisterPanel() {
        addComponents();
        attachActions();
    }

    public MaterialRegisterPanel(MaterialDTO type) {
        super(type);
        addComponents();
        attachActions();
    }

    @Override
    public boolean save() {
        String materialName = this.materialName.getText();
        Integer materialQuantity = (Integer) this.materialQuantity.getValue();
        Integer minMaterialQuantity = (Integer) this.minMaterialQuantity.getValue();
        String managerName = this.managerName.getText();

        MaterialDTO dto = new MaterialDTO();
        dto.setName(materialName);
        dto.setQuantity(materialQuantity);
        dto.setMinQuantity(minMaterialQuantity);
        try {
            EmployeeDTO employeeDTO = EmployeeController.INSTANCE.getByNameIfRoleIdMatches(managerName, id -> id == 3 || id == 1);
            dto.setEmployee(employeeDTO);

            MaterialController.INSTANCE.insert(dto);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro : %s".formatted(e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public boolean edit() {
        String materialName = this.materialName.getText();
        Integer materialQuantity = (Integer) this.materialQuantity.getValue();
        Integer minMaterialQuantity = (Integer) this.minMaterialQuantity.getValue();
        String managerName = this.managerName.getText();

        MaterialDTO dto = new MaterialDTO();
        dto.setId(getType().getId());
        dto.setName(materialName);
        dto.setQuantity(materialQuantity);
        dto.setMinQuantity(minMaterialQuantity);
        try {
            EmployeeDTO employeeDTO = EmployeeController.INSTANCE.getByNameIfRoleIdMatches(managerName, id -> id == 3 || id == 1);
            dto.setEmployee(employeeDTO);


            MaterialController.INSTANCE.update(dto);
            return true;
        } catch (InvalidValueException e) {
            JOptionPane.showMessageDialog(null, "Erro : %s".formatted(e.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }


    @Override
    public void addComponents() {
        setLayout(new GridLayout(4, 1));

        materialName = new JTextField(32);
        materialQuantity = new JSpinner();
        minMaterialQuantity = new JSpinner();
        managerName = new JTextField(32);

        add(ViewUtils.makeFieldAndLabel("Nome", materialName));
        add(ViewUtils.makeFieldAndLabel("Quantidade", materialQuantity));
        add(ViewUtils.makeFieldAndLabel("Quantidade Minima", minMaterialQuantity));
        add(ViewUtils.makeFieldAndLabel("Nome do Gerente", managerName));

        if(getType() != null){
            materialName.setText(getType().getName());
            materialQuantity.setValue(getType().getQuantity());
            minMaterialQuantity.setValue(getType().getMinQuantity());
            managerName.setText(getType().getEmployee().getLogin().getUsername());
        }
    }

    @Override
    public void attachActions() {

    }
}
