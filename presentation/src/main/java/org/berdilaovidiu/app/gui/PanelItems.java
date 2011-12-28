package org.berdilaovidiu.app.gui;

import net.miginfocom.swing.MigLayout;
import org.berdilaovidiu.app.gui.core.Item;
import org.berdilaovidiu.app.gui.core.TextItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ovidiu
 * Date: 12/9/11
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PanelItems extends JPanel{
    private DefaultListModel model;
    private JPanel searchPanel;
    private JPanel listPanel;
    private ItemList itemList;

    private JButton buttonSearch;
    private JTextField fieldSearch;

    public PanelItems(DefaultListModel model) {
        this.model = model;
        itemList = new ItemList();
        itemList.setModel(model);
        itemList.setModel(this.model);
        initComponents();
        initLayouts();
        initActions();

    }

    private void initComponents() {
        searchPanel = new JPanel();
        listPanel = new JPanel();
        buttonSearch = new JButton("Search");
        fieldSearch = new JTextField("Type word here");
    }

    private void initLayouts() {
        searchPanel.setLayout(new MigLayout("wrap 2"));
        searchPanel.add(fieldSearch);
        searchPanel.add(buttonSearch);


//        listPanel.setLayout(new MigLayout("fill"));
        listPanel.add(itemList);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(listPanel);

        this.setLayout(new MigLayout("wrap 1, fill"));
        this.add(searchPanel, "growx");
        this.add(scrollPane, "grow");
    }

    private void initActions() {


    }


    public void addNewItem(TextItem item) {
        Item comp = new Item(5, 20);
        comp.setSize(100, 50);
        comp.setPreferredSize(new Dimension(100, 50));

        System.out.println("element added");
        ((DefaultListModel)(itemList.getModel())).add(0,"sdfasdfasdf");
        listPanel.validate();
    }
}
