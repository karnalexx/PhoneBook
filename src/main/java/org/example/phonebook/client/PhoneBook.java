package org.example.phonebook.client;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.example.phonebook.shared.model.IUser;
import org.example.phonebook.shared.model.User;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PhoneBook implements EntryPoint {

    private final UserServiceAsync userService = GWT.create(UserService.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final TextBox loginField = new TextBox();
        loginField.setFocus(true);
        final TextBox phoneField = new TextBox();
        final Button saveButton = new Button("Save");

        final FlexTable flexTable = new FlexTable();
        flexTable.setBorderWidth(1);
        flexTable.setTitle("Phone Book");
        flexTable.setWidth("100%");
        flexTable.setHTML(0, 0, "<b>Id</b>");
        flexTable.setHTML(0, 1, "<b>Login</b>");
        flexTable.setHTML(0, 2, "<b>Phone</b>");
        flexTable.setHTML(0, 3, "");
        //flexTable.getFlexCellFormatter().setColSpan(0, 3, 2);
        flexTable.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
                HTMLTable.Cell cell = flexTable.getCellForEvent(event);
                final int row = cell.getRowIndex();
                final int column = cell.getCellIndex();
                final TextBox textBox = new TextBox();
                textBox.setText(flexTable.getText(row, column));
                flexTable.setWidget(row, column, textBox);
                textBox.setFocus(true);
                textBox.addKeyDownHandler(new KeyDownHandler() {
                    @Override
                    public void onKeyDown(KeyDownEvent keyDownEvent) {
                        int code = keyDownEvent.getNativeKeyCode();
                        if (KeyCodes.KEY_ENTER == code) {
                            String login = null, phone = null;
                            if (column == 1) {
                                login = textBox.getText();
                                phone = flexTable.getText(row, 2);
                            } else if (column == 2) {
                                login = flexTable.getText(row, 1);
                                phone = textBox.getText();
                            }
                            userService.update(Integer.valueOf(flexTable.getText(row, 0)), login, phone, new AsyncCallback<Void>() {
                                @Override
                                public void onFailure(Throwable throwable) {
                                    Window.alert("Unable to update user.");
                                    flexTable.setWidget(row, column, new Label(flexTable.getText(row, column)));
                                }

                                @Override
                                public void onSuccess(Void aVoid) {
                                    flexTable.setWidget(row, column, new Label(textBox.getText()));
                                }
                            });
                        }
                    }
                });
            }
        });

        RootPanel.get("login").add(loginField);
        RootPanel.get("phone").add(phoneField);
        RootPanel.get("btnSave").add(saveButton);
        RootPanel.get().add(flexTable);

        class SaveUserHandler implements ClickHandler, KeyUpHandler {

            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    saveUser();
                }
            }

            public void onClick(ClickEvent arg0) {
                saveUser();
            }

            private void saveUser() {
                String login = loginField.getText();
                String phone = phoneField.getText();
                //saveButton.setEnabled(false);

                userService.create(login, phone, new AsyncCallback<IUser>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("Unable to create user.");
                    }

                    @Override
                    public void onSuccess(IUser user) {
                        final int rowCount = flexTable.getRowCount();
                        flexTable.setText(rowCount, 0, String.valueOf(user.getId()));
                        flexTable.setText(rowCount, 1, user.getLogin());
                        flexTable.setText(rowCount, 2, user.getPhone());
                        Button delete = new Button("Delete");
                        delete.addClickHandler(new ClickHandler() {
                            @Override
                            public void onClick(final ClickEvent clickEvent) {
                                final int rowIndex = flexTable.getCellForEvent(clickEvent).getRowIndex();
                                userService.delete(Integer.valueOf(flexTable.getText(rowIndex, 0)), new AsyncCallback<Void>() {
                                    @Override
                                    public void onFailure(Throwable throwable) {
                                        Window.alert("Unable to delete user.");
                                        throwable.printStackTrace();
                                    }

                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        flexTable.removeRow(rowIndex);
                                    }
                                });
                            }
                        });
                        flexTable.setWidget(rowCount, 3, delete);
                    }
                });
            }
        }

        SaveUserHandler saveBookHandler = new SaveUserHandler();
        saveButton.addClickHandler(saveBookHandler);
    }
}
