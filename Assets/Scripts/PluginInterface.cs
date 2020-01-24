using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PluginInterface : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log(SettingsPlugin.GetHomePackage());
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
