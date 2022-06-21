import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.util.concurrent.atomic.AtomicReference;

public class AddImageButton extends JButton {
    public AddImageButton(int x, int y, int width, int height, AtomicReference<String> pathToImage) {
        super("Add Image");
        super.setBounds(x, y, width, height);
        this.addActionListener((event) -> {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                pathToImage.set(j.getSelectedFile().getAbsolutePath());
            }

        });
    }
}
