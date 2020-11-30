package khan.academy.appiumserverconfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class LaunchAppiumServer {

	private static AppiumDriverLocalService appiumservice;

	private static Process process;

	public static AppiumDriverLocalService startServer()
			throws NumberFormatException, AppiumServerHasNotBeenStartedLocallyException, Exception {

		appiumservice = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File(readProperties().getProperty("nodePath")))
						.withAppiumJS(new File(readProperties().getProperty("pathAppiumMainJs")))
						.withIPAddress("0.0.0.0").usingPort(8888));

		if ((checkIfServerIsRunnning(Integer.parseInt(readProperties().get("appiumPort").toString())))) {
			String[] cmd = { "killall", "node" };
			// process = new ProcessBuilder(cmd).start();
			// process.waitFor(180, TimeUnit.SECONDS);
			// printResults(process);
			appiumservice.start();
			System.out.println("server started");
		} else {
			appiumservice.start();
			System.out.println("appium service started");
		}

		return appiumservice;
	}

	public static void printResults(Process process1) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process1.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void stopServer(AppiumDriverLocalService service) {
		appiumservice.stop();
		System.out.println("service stoped");
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.isClosed();
		} catch (UnknownHostException e) { // unknown host
			isServerRunning = true;

		}

		catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} catch (NullPointerException e) {
			isServerRunning = true;

		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

}
