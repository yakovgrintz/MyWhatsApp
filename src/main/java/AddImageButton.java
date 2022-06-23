import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.util.concurrent.atomic.AtomicReference;

public class AddImageButton extends JButton {
    public AddImageButton(int x, int y, int width, int height, AtomicReference<String> pathToImage) {
        super("Add Image");
        super.setBounds(x, y, width, height);
        this.addActionListener((event) -> {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                pathToImage.set(j.getSelectedFile().getAbsolutePath());
            }

        });
    }
}
