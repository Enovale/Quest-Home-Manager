using UnityEngine;

public class SettingsPlugin
{
    public SettingsPlugin()
    {

    }

    public static string InstallEnvironment()
    {
        AndroidJNIHelper.debug = true;

        if (Application.platform == RuntimePlatform.Android)
        {
            using (var javaUnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
            {
                using (var currentActivity = javaUnityPlayer.GetStatic<AndroidJavaObject>("currentActivity"))
                {
                    using (var androidPlugin = new AndroidJavaObject("com.elijahzawesome.homeplugin.AndroidPlugin", currentActivity))
                    {
                        androidPlugin.Call("SetEnvironment", "testscene.zip", "com.oculus.environment.prod.winterlodge");
                        //androidPlugin.Call("SetEnvironment");
                        return "ran code. hope it worked.";
                    }
                }
            }
        } 
        else
        {
            return "currently not running on android.";
        }
    }
}