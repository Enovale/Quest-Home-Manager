using UnityEngine;

public class SettingsPlugin
{
    public SettingsPlugin()
    {

    }

    public static string GetHomePackage()
    {
        AndroidJNIHelper.debug = true;

        if (Application.platform == RuntimePlatform.Android)
        {
            using (AndroidJavaClass jc = new AndroidJavaClass("com.elijahzawesome.homeplugin.AndroidPlugin"))
            {
                return jc.Call<string>("GetHomeUri");
            }
        } 
        else
        {
            return "currently not running on android.";
        }

        return "error";
    }
}